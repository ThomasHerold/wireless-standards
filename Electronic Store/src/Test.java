
public class Test {

	public static void main(String[] args) {
		String s = "select  sum(i.sellingprice) "
				+ "from transactions t, transactiondetails td, items i "
				+ "where t.transactionid = td.transactionid and td.itemid = i.itemid "
				+ "and t.transactiontype  like 'Sale' ";
		System.out.println(s.replace("\"", " ").replace("\n", " ").replace("+", " "));
	}
}
