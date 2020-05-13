import java.sql.*;
import java.util.ArrayList;

public class DbHelper {
    private static String user="root";
    private static String password="12345";
    private static String dbName="obs";
    
    private static final String CONNECT_STRING="jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static final String TABLO_ADI="notlar";
    private static final String TABLO_SUTUN_1="id";
    private static final String TABLO_SUTUN_2="DersAdi";
    private static final String TABLO_SUTUN_3="VizeNotu";
    private static final String TABLO_SUTUN_4="FinalNotu";
    private static final String TABLO_SUTUN_5="Ortalama";
    private static final String TABLO_SUTUN_6="HarfNotu";
    private static final String TABLO_SUTUN_7="Durum";
    
    public DbHelper(){};
    
    private static DbHelper instance=new DbHelper();
    
    public static DbHelper getInstance(){
        return instance;
    }
     Connection connection;
    
    public Connection openConnection(){
        try {
            connection=DriverManager.getConnection(CONNECT_STRING,user,password);
            System.out.println("Veritabanına Bağlandı...");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return connection;
    }
    
    public void closeConnection(){
        try {
            if(connection != null){
                
                connection.close();
                System.out.println("Veritabanı Kapatıldı");
            }
            else{
                System.out.println("Kapatılamadı");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Notlar> tumNotlariGetir(){
        Statement statement=null;
        try {
            connection=DriverManager.getConnection(CONNECT_STRING,user,password);
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM "+TABLO_ADI);
            ArrayList<Notlar> notlars=new ArrayList<>();
            
            while (resultSet.next()) {
                Notlar notlar=new Notlar();
                notlar.setId(resultSet.getInt(TABLO_SUTUN_1));
                notlar.setDersAdi(resultSet.getString(TABLO_SUTUN_2));
                notlar.setVizeNotu(resultSet.getInt(TABLO_SUTUN_3));
                notlar.setFinalNotu(resultSet.getInt(TABLO_SUTUN_4));
                notlar.setOrtalama(resultSet.getDouble(TABLO_SUTUN_5));
                notlar.setHarfNotu(resultSet.getString(TABLO_SUTUN_6));
                notlar.setDurum(resultSet.getString(TABLO_SUTUN_7));
                notlars.add(notlar);
            }
            return notlars;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}