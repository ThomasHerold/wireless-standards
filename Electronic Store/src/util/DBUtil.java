package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Cellphone;
import model.Headphones;
import model.Item;
import model.SimCard;
import model.Transaction;

public class DBUtil {

	private static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed())
				connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	private static void connect() {
		try {
			Class.forName(Constants.DRIVER);
			connection = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD);
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static boolean storePurchase(Transaction transaction) {
		Connection connection = DBUtil.getConnection();
		try {

			PreparedStatement ps = connection.prepareStatement(
					"insert into transactions (transactioncode, name, email, transactiontime, transactiontype) values (?,?,?,?,?)");
			ps.setString(1, transaction.getTransactionCode());
			ps.setString(2, transaction.getName());
			ps.setString(3, transaction.getEmail());
			ps.setDate(4, new Date(transaction.getTime().getTime()));
			ps.setString(5, "Purchase");
			ps.executeUpdate();
			ps.close();

			int transactiondid = getLatestTransactionID();

			ArrayList<Item> items = transaction.getItems();

			for (Item item : items) {
				ps = connection
						.prepareStatement("insert into items (itemtypeid,purchaseprice,sellingprice) values (?,?,?)");
				ps.setInt(1, getItemTypeId(item));
				ps.setDouble(2, item.getPurchasePrice());
				ps.setDouble(3, item.getSellingPrice());
				ps.executeUpdate();
				ps.close();
				int itemid = getLatestItemID();

				ps = connection.prepareStatement("insert into transactiondetails values (?,?)");
				ps.setInt(1, transactiondid);
				ps.setInt(2, itemid);
				ps.executeUpdate();

				if (item instanceof Cellphone) {
					ps = connection.prepareStatement(
							"insert into cellphones (itemid,imei,brand,model,color) values (?,?,?,?,?)");
					ps.setInt(1, itemid);
					ps.setString(2, ((Cellphone) item).getImei());
					ps.setString(3, ((Cellphone) item).getBrand());
					ps.setString(4, ((Cellphone) item).getModel());
					ps.setString(5, ((Cellphone) item).getColor());
					ps.executeUpdate();
					ps.close();
				} else if (item instanceof Headphones) {
					ps = connection.prepareStatement(
							"insert into headphones (itemid,serial,brand,model,color,hasmicrophone) values (?,?,?,?,?,?)");
					ps.setInt(1, itemid);
					ps.setString(2, ((Headphones) item).getSerial());
					ps.setString(3, ((Headphones) item).getBrand());
					ps.setString(4, ((Headphones) item).getModel());
					ps.setString(5, ((Headphones) item).getColor());
					ps.setBoolean(6, ((Headphones) item).isHasMicrophone());
					ps.executeUpdate();
					ps.close();
				} else if (item instanceof SimCard) {
					ps = connection
							.prepareStatement("insert into simcards (itemid,iccid,network,size) values (?,?,?,?)");
					ps.setInt(1, itemid);
					ps.setString(2, ((SimCard) item).getIccid());
					ps.setString(3, ((SimCard) item).getNetwork());
					ps.setString(4, ((SimCard) item).getSize());
					ps.executeUpdate();
					ps.close();
				}
			}

			connection.commit();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		return false;
	}

	private static int getLatestTransactionID() {
		Connection connection = DBUtil.getConnection();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select transactionid from transactions order by transactionid desc limit 1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("transactionid");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	private static int getLatestItemID() {
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select itemid from items order by itemid desc limit 1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("itemid");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	private static int getItemTypeId(Item item) {
		if (item instanceof Cellphone)
			return 1;
		else if (item instanceof Headphones)
			return 2;
		else
			return 3;
	}

	public static Cellphone getCellphone(String imei) {
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from cellphones natural join items where imei = ?");
			ps.setString(1, imei);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new Cellphone(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"), rs.getBoolean("sold"),
						rs.getString("imei"), rs.getString("brand"), rs.getString("model"), rs.getString("color"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Headphones getHeadphones(String serial) {
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from headphones natural join items where serial = ?");
			ps.setString(1, serial);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new Headphones(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
						rs.getBoolean("sold"), rs.getString("serial"), rs.getString("brand"), rs.getString("model"),
						rs.getString("color"), rs.getBoolean("hasmicrophone"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static SimCard getSimCard(String iccid) {
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from simcards natural join items where iccid = ?");
			ps.setString(1, iccid);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new SimCard(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"), rs.getBoolean("sold"),
						rs.getString("iccid"), rs.getString("network"), rs.getString("size"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int getItemId(Item item) {
		String sql;
		if (item instanceof Cellphone)
			sql = "select itemid from cellphones natural join items where imei = " + ((Cellphone) item).getImei();
		else if (item instanceof Headphones)
			sql = "select itemid from headphones natural join items where serial = " + ((Headphones) item).getSerial();
		else
			sql = "select itemid from simcards natural join items where iccid = " + ((SimCard) item).getIccid();

		connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("itemid");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;

	}

	public static boolean storeSale(Transaction transaction) {
		Connection connection = DBUtil.getConnection();
		try {

			PreparedStatement ps = connection.prepareStatement(
					"insert into transactions (transactioncode, name, email, transactiontime, transactiontype) values (?,?,?,?,?)");
			ps.setString(1, transaction.getTransactionCode());
			ps.setString(2, transaction.getName());
			ps.setString(3, transaction.getEmail());
			ps.setDate(4, new Date(transaction.getTime().getTime()));
			ps.setString(5, "Sale");
			ps.executeUpdate();
			ps.close();

			int transactiondid = getLatestTransactionID();

			ArrayList<Item> items = transaction.getItems();

			for (Item item : items) {
				int itemid = getItemId(item);
				ps = connection.prepareStatement("update items set sold = 1 where itemid = ?");
				ps.setInt(1, itemid);
				ps.executeUpdate();
				ps.close();

				ps = connection.prepareStatement("insert into transactiondetails values (?,?)");
				ps.setInt(1, transactiondid);
				ps.setInt(2, itemid);
				ps.executeUpdate();
			}

			connection.commit();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		}

		return false;
	}

	public static ArrayList<Item> getAllCellphones() {
		ArrayList<Item> items = new ArrayList<>();

		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from cellphones natural join items");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				items.add(new Cellphone(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
						rs.getBoolean("sold"), rs.getString("imei"), rs.getString("brand"), rs.getString("model"),
						rs.getString("color")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Item> getAllHeadphones() {
		ArrayList<Item> items = new ArrayList<>();

		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from headphones natural join items");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				items.add(new Headphones(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
						rs.getBoolean("sold"), rs.getString("serial"), rs.getString("brand"), rs.getString("model"),
						rs.getString("color"), rs.getBoolean("hasmicrophone")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Item> getAllSimCards() {
		ArrayList<Item> items = new ArrayList<>();

		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from simcards natural join items");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				items.add(new SimCard(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
						rs.getBoolean("sold"), rs.getString("iccid"), rs.getString("network"), rs.getString("size")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static Transaction getTransaction(String code) {
		Connection connection = getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from transactions where transactioncode = ?");
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Transaction transaction = new Transaction(rs.getString("transactioncode"), rs.getString("name"),
						rs.getString("email"), rs.getString("transactiontype").equalsIgnoreCase("Purchase"));
				transaction.setTime(new java.util.Date(rs.getDate("transactiontime").getTime()));
				int transactionID = rs.getInt("transactionid");

				ps = connection.prepareStatement("select * from transactiondetails where transactionid = ?");
				ps.setInt(1, transactionID);
				rs = ps.executeQuery();
				while (rs.next()) {
					transaction.addItem(getItem(rs.getInt("itemid")));
				}

				return transaction;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Transaction> getAllTransactions() {
		Connection connection = getConnection();
		ArrayList<Transaction> transactions = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from transactions");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getString("transactioncode"), rs.getString("name"),
						rs.getString("email"), rs.getString("transactiontype").equalsIgnoreCase("Purchase"));
				transaction.setTime(new java.util.Date(rs.getDate("transactiontime").getTime()));
				int transactionID = rs.getInt("transactionid");

				ps = connection.prepareStatement("select * from transactiondetails where transactionid = ?");
				ps.setInt(1, transactionID);
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					transaction.addItem(getItem(rs2.getInt("itemid")));
				}

				transactions.add(transaction);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

	private static Item getItem(int itemid) {
		Connection connection = getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement("select itemtypeid from items where itemid = ?");
			ps.setInt(1, itemid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int itemtype = rs.getInt("itemtypeid");
				String sql;
				if (itemtype == 1)
					sql = "select * from cellphones natural join items where itemid = ?";
				else if (itemtype == 2)
					sql = "select * from headphones natural join items where itemid = ?";
				else
					sql = "select * from simcards natural join items where itemid = ?";

				ps = connection.prepareStatement(sql);
				ps.setInt(1, itemid);
				rs = ps.executeQuery();
				rs.next();
				if (itemtype == 1)
					return new Cellphone(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
							rs.getBoolean("sold"), rs.getString("imei"), rs.getString("brand"), rs.getString("model"),
							rs.getString("color"));
				else if (itemtype == 2)
					return new Headphones(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
							rs.getBoolean("sold"), rs.getString("serial"), rs.getString("brand"), rs.getString("model"),
							rs.getString("color"), rs.getBoolean("hasmicrophone"));
				else
					return new SimCard(rs.getDouble("purchaseprice"), rs.getDouble("sellingprice"),
							rs.getBoolean("sold"), rs.getString("iccid"), rs.getString("network"),
							rs.getString("size"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public static double totalProfitLoss() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  sum(i.sellingprice) - sum(i.purchaseprice)" + "from transactions t, transactiondetails td, items i "
							+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
							+ "and t.transactiontype  like 'Sale' ");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	public static double totalSales() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  sum(i.sellingprice) " + "from transactions t, transactiondetails td, items i "
							+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
							+ "and t.transactiontype  like 'Sale' ");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	public static double totalPurchases() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  sum(i.purchaseprice) " + "from transactions t, transactiondetails td, items i "
							+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
							+ "and t.transactiontype  like 'Purchase' ");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	public static double inventoryValue() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select sum(i.sellingprice) from transactions t, transactiondetails td, items i "
					+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
					+ "and t.transactiontype  like 'PURCHASE' "
					+ "and td.itemid NOT IN (select itemid from transactions t, transactiondetails td "
					+ "where t.transactionid = td.transactionid  and t.transactiontype  like 'Sale');");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}
	
	public static double totalSalesToday() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  sum(i.sellingprice) " + "from transactions t, transactiondetails td, items i "
							+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
							+ "and t.transactiontype  like 'Sale' and t.transactiontime = ?");
			ps.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}
	
	public static double totalPurchaseToday() {
		Connection connection = getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  sum(i.purchaseprice) " + "from transactions t, transactiondetails td, items i "
							+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
							+ "and t.transactiontype  like 'Purchase' and t.transactiontime = ?");
			ps.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0.0;
	}


}
