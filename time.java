import java.util.Date;
import java.text.SimpleDateFormat; 
class time
{
public static void main(String args[])
{
String pattern = "dd-MM-yyyy";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

String date = simpleDateFormat.format(new Date());
System.out.println(date);
}}