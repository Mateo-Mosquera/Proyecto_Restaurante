import DataAccess.ClienteDAO;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ClienteDAO cDao = new ClienteDAO();
            for (ClienteDAO c : cDao.readAll()) {
                System.out.println(c.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
