import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat; 
import java.util.Date;  

class BugDetails 
{
	
	protected String date;
	protected int bug_id;
	protected String bug_title;
	protected String bug_cat;
	protected String bug_des;
	protected String bug_status;
	
	BugDetails()
	{
		date="";
		bug_id=0;
		bug_title="";
		bug_cat="";
		bug_des="";
		bug_status="pending";
	}
	BugDetails(String d,int id,String title,String cat,String des,String status)
	{
		date=d;
		bug_id=id;
		bug_title=title;
		bug_cat=cat;
		bug_des=des;
		bug_status=status;
	}
	public int get_bugid(){ return bug_id; }
	public String get_bugtitle(){ return bug_title; }
	public String get_bugcat(){ return bug_cat; }
	public String get_bugdes(){ return bug_des; }
	public String get_bugstatus(){ return bug_status; }
	
	public void set_bugid(int id){ bug_id=id; }
	public void set_bugtitle(String title){ bug_title=title; }
	public void set_bugcategory(String cat){ bug_cat=cat; }
	public void set_bugstatus(String status){ bug_status=status; }	
	public void set_bugdes(String des){ bug_des=des; }	
	/*public void set_issue_date(Date obj)
	{
		issued.set_day(obj.get_day());
		issued.set_month(obj.get_month());
		issued.set_year(obj.get_year());
	}*/
}

class User extends BugDetails
{

	private String user_id;
	protected String user_name;
	private String pwd;
	
	User()
	{
		super();
		user_id="";
		user_name="";
		pwd="";
	}
	User(String user_id,String user_name,String d,int id,String title,String cat,String des,String status)
	{
		super(d,id,title,cat,des,status);
		this.user_id=user_id;
		this.user_name=user_name;
		//this.pwd=pwd;
	}
	
	public void setUserId(String id){user_id=id;}
	public void setUserName(String s){user_name=s;}
	public void setpwd(String p){pwd=p;}
	public String getUserId(){ return user_id; }
	public String getUserName(){ return user_name; }
	public String getpwd(){ return pwd; }

}

class nameCompare implements Comparator<User>
{
    
    public int compare(User s1, User s2)
    {
        return s1.user_name.compareTo(s2.user_name);
    }
}
 
class categoryCompare implements Comparator<User>
{
   
    public int compare(User s1, User s2)
    {
        return s1.bug_cat.compareTo(s2.bug_cat);
    }
}
 
class Test
{
	public static void main(String args[])throws IOException
	{
		try
		{	BufferedReader br=new BufferedReader(new InputStreamReader(System.in)) ;
			int x,ch,y,z,a,b,h;
			do
			{
				System.out.println("\n1)Press 1 if you'r an User\n2)Press 2 if you'r an admin\n3)Press 3 to exit.");
				ch=Integer.parseInt(br.readLine());

				switch(ch)
				{

					case 1:
					do
						{
						System.out.println("\n1)Press 1 to login\n2)Press 2 to sign in if you are a new user\n3)Press 3 to go to main screen ");
						 x=Integer.parseInt(br.readLine());
							switch(x)
							{
								case 1:BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream("user.txt")));
									User u=new User();
									System.out.print("\nEnter user_name:");
									String str=br.readLine();
									u.setUserName(str);
									
									System.out.print("Enter passwrd:");
									String pwd=br.readLine();
									u.setpwd(pwd);int flag=0;
									//System.out.println(u.getUserName()+ " "+u.getpwd());
									String line=br1.readLine();
									while(line!=null)
									{
										String cols[]=line.split(",");
										//System.out.println(cols[0]+" "+cols[1]+" "+cols[2]);
										if((cols[1].equals(u.getUserName())) && (cols[2].equals(u.getpwd())))
										{
											u.setUserId(cols[0]);
											System.out.println("\n************WELCOME to Defect Tracking System***********!!");
											 flag=1;
											do{
											System.out.println("\n1)Press 1 to report a new defect\n2)Press 2 to view all defect\n3)press 3 to track a defect\n4)Press 4 to logout");
											y=Integer.parseInt(br.readLine());
											
											switch(y)
											{
												case 1:BufferedWriter writer = new BufferedWriter(new FileWriter																		("bugdetails.txt", true));
													u.bug_id=(int)((Math.random() * 9000000)+1000000);
													System.out.print("\nEnter Defect title:");
													String ti=br.readLine();u.bug_title=ti;
													System.out.print("\nEnter Defect category: ");
													String c=br.readLine();u.bug_cat=c;
													System.out.print("\nEnter Defect Description in a line: ");
													String d=br.readLine();u.bug_des=d;
											SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
											String strDate= formatter.format(new Date());
													u.date=strDate;
													writer.newLine();
												writer.write(u.date+","+u.getUserId()+","+u.getUserName()+","+u.bug_id+","+u.bug_title+","+u.bug_cat+","+u.bug_des+","+u.bug_status);
													writer.close();
													System.out.println("\nYour Defect has been successfullly registered!!!\nThe tracking_id of the defect is: " +u.bug_id +"\nYou can use this id to track your defect.");
													break;

												case 2:BufferedReader br2=new BufferedReader(new InputStreamReader(new 														FileInputStream("bugdetails.txt")));
													String l=br2.readLine();int f=0,count=0;
													while(l!=null)
													{
														String col[]=l.split(",");
														if(col[2].equals(u.getUserName()))
{if(count==0)System.out.println("UserId"+"\t\t"+"User_Name"+"\t\t"+"Defect_Id"+"\t\t"+"Title"+"\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
System.out.println(col[1]+"\t\t"+col[2]+"\t\t\t"+col[3]+"\t\t\t"+col[4]+"\t\t"+col[5]+"\t\t"+col[6]+"\t\t"+col[7]);f=1;count=1;}
														l=br2.readLine();
													}
													if(f==0)
													System.out.println("No records found\n");
													br2.close();
													break;
												case 3:BufferedReader br3=new BufferedReader(new InputStreamReader(new 														FileInputStream("bugdetails.txt")));
													System.out.println("\nEnter the Tracking_Id of the defect :");
													long j=Long.parseLong(br.readLine());
													String li=br3.readLine();int flag2=0;
													while(li!=null)
													{
														String co[]=li.split(",");
														if(Long.parseLong(co[3])==j)
{System.out.println("UserId"+"\t\t"+"User_Name"+"\t\t"+"Bug_Id"+"\t\t"+"Title"+"\t\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
System.out.println(co[1]+"\t\t"+co[2]+"\t\t\t"+co[3]+"\t\t"+co[4]+"\t\t"+co[5]+"\t\t\t"+co[6]+"\t\t"+co[7]);flag2=1;}
													li=br3.readLine();
													}
													if(flag2==0)
													System.out.println("No records found");	
													br3.close();
													break;
												case 4:break;
												default:System.out.println("Enter valid choice");
													break;
											}
										    }while(y!=4);
													
										}
										line=br1.readLine();
									}
									if(flag==0)
									System.out.println("\nInvalid user name or password..!!!");
									br1.close();
									break;


								case 2:BufferedWriter out = new BufferedWriter(new FileWriter("user.txt", true));
									User u1=new User();int flag1=0;
									System.out.print("\nEnter user_id: ");
									String i=(br.readLine());
									u1.setUserId(i);
									System.out.print("\nEnter user_name:");
									String s=br.readLine();
									u1.setUserName(s);
									System.out.print("\nEnter passwrd:");
									String pw=br.readLine();
									while(flag1!=1)
									{		
										System.out.print("\nReconfirm password: ");
										String p=br.readLine();
										if(pw.equals(p))
										{
											flag1=1;
											u1.setpwd(pw);
											System.out.println("\nAccount created Successfully!!");
											out.newLine();
											out.write(u1.getUserId()+","+u1.getUserName()+","+u1.getpwd());
											//out.newLine();
											out.close();
										}
										else
										System.out.println("Password did not match!!!");
									}
									break;
								case 3: break;
								default:System.out.println("Enter valid choice!!");
									break;
							}
						}while(x!=3);
						break;

						case 2:BufferedReader br4=new BufferedReader(new InputStreamReader(new FileInputStream("user.txt")));
							User u2=new User();
							System.out.print("\nEnter admin_name:");
							String st=br.readLine();
							u2.setUserName(st);
							System.out.print("Enter passwrd:");
							String pass=br.readLine();
							u2.setpwd(pass);int flag3=0;
							String input=br4.readLine();
							while(input!=null)
							{
								String colm[]=input.split(",");
								if((colm[1].equals(u2.getUserName())) && (colm[2].equals(u2.getpwd())))
								{
									u2.setUserId(colm[0]);
									System.out.println("\n**************WELCOME to Defect Tracking System****************!!");
									flag3=1;
							
									do
									{
										System.out.println("\n1)Press 1 to View Defects\n2)Press 2 to Sort Defects\n3)Press 3 to Update status of Defect\n4)Press 4 to logout");
										a=Integer.parseInt(br.readLine());
										switch(a)
										{
											case 1:
System.out.println("\n1)press 1 to view all defects\n2)Press 2 to view defects by a certain user\n3)Press 3 to view defects of a certain category");
											b=Integer.parseInt(br.readLine());
											switch(b)
											{
											case 1:BufferedReader br5=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
											
System.out.println("UserId"+"\t\t"+"User_Name"+"\t\t"+"Bug_Id"+"\t\t"+"Title"+"\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
												String in=br5.readLine();
												while(in!=null)
												{
													String div[]=in.split(",");
System.out.println(div[1]+"\t\t"+div[2]+"\t\t\t"+div[3]+"\t\t"+div[4]+"\t\t"+div[5]+"\t\t\t"+div[6]+"\t\t"+div[7]);
													in=br5.readLine();
												}
												br5.close();
											break;
											case 2:BufferedReader br6=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
											
												System.out.print("\nEnter user name:");
												String n=br.readLine();int flag4=0,count1=0;
												String i=br6.readLine();
												while(i!=null)
												{
													String d[]=i.split(",");	
													
													if(d[2].equals(n))
			
{if(count1==0)System.out.println("UserId"+"\t\t"+"User_Name"+"\t\t"+"Bug_Id"+"\t\t"+"Title"+"\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
System.out.println(d[1]+"\t\t"+d[2]+"\t\t\t"+d[3]+"\t\t"+d[4]+"\t\t"+d[5]+"\t\t\t"+d[6]+"\t\t"+d[7]);flag4=1;count1=1;}
													i=br6.readLine();
												}
												if(flag4==0)
												System.out.println("No records found!!");
												br6.close();
											break;
											case 3:BufferedReader br7=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
											
												System.out.print("\nEnter category name:");
												String ct=br.readLine();int flag5=0,count2=0;
												String inp=br7.readLine();
												while(inp!=null)
												{
													String di[]=inp.split(",");//System.out.println(di[5]+" "+ct);
													
{if(count2==0)System.out.println("UserId"+"\t\t"+"User_Name"+"\t\t"+"Defect_Id"+"\t\t"+"Title"+"\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
System.out.println(di[1]+"\t\t"+di[2]+"\t\t\t"+di[3]+"\t\t"+di[4]+"\t\t"+di[5]+"\t\t\t"+di[6]+"\t\t"+di[7]);flag5=1;count2=1;}
													inp=br7.readLine();
												}
												if(flag5==0)
												System.out.println("No records found!!");
												br7.close();
												break;

											default:System.out.println("Enter valid choice");
												break;
											     }  
												break;
											case 2:System.out.println("\n1)Press 1 to sort by user name\n2)press 2 to sort by defect category");
												h=Integer.parseInt(br.readLine());
												if(h==1)
												{BufferedReader br8=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
												    ArrayList<User> userRecords = new ArrayList<User>();
												   String r=br8.readLine();
												   while(r!=null)
												   {
													String[] userDetail = r.split(",");
userRecords.add(new User(userDetail[1], userDetail[2],userDetail[0],Integer.parseInt(userDetail[3]),userDetail[4],userDetail[5],userDetail[6],userDetail[7]));
             												r=br8.readLine();
												   }
													Collections.sort(userRecords, new nameCompare());
System.out.println("User_Name"+"\t"+"UserId"+"\t\t"+"Defect_Id"+"\t\t"+"Title"+"\t\t"+"Category"+"\t\t"+"Description"+"\t\t"+"Status");
													 for (User us : userRecords) 
        												{
           													System.out.println(us.user_name+"\t\t "+us.getUserId()+"\t\t "+us.bug_id+" \t\t"+us.bug_title+"\t\t "+us.bug_cat+"\t\t "+us.bug_des+"\t\t "+us.bug_status);
        												}
												 br8.close();
												}
												else if(h==2)
												{
											BufferedReader br9=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
													ArrayList<User> Records = new ArrayList<User>();
												   String r1=br9.readLine();
												   while(r1!=null)
												   {
													String[] Detail = r1.split(",");
Records.add(new User(Detail[1], Detail[2],Detail[0],Integer.parseInt(Detail[3]),Detail[4],Detail[5],Detail[6],Detail[7]));
             												r1=br9.readLine();
												   }
													Collections.sort(Records, new categoryCompare());
System.out.println("Category"+"\t\t"+"UserId"+"\t\t"+"User_Name"+"\t\t"+"Defect_Id"+"\t\t"+"Title"+"\t\t"+"Description"+"\t\t"+"Status");
													 for (User use : Records) 
        												{
           													System.out.println(use.bug_cat+"\t\t "+use.getUserId()+"\t\t "+use.user_name+" \t\t"+use.bug_id+"\t\t "+use.bug_title+"\t\t "+use.bug_des+"\t\t "+use.bug_status);
        												}
												br9.close();
												}
												else
												{System.out.println("Enter Valid choice!!");
												break;}
											  break;

											case 3:File oldFile=new File("bugdetails.txt");
												File newFile=new File("temp.txt");
												System.out.println("Enter tracking id of defect");
												int i_d=Integer.parseInt(br.readLine());
												System.out.println("Enter Action status");
												String status=br.readLine();
												
												try
												{
												FileWriter fw=new FileWriter("temp.txt",true);
													BufferedWriter bw=new BufferedWriter(fw);
													PrintWriter pw=new PrintWriter(bw);
								BufferedReader br9=new BufferedReader(new InputStreamReader(new FileInputStream("bugdetails.txt")));
													String r2=br9.readLine();
												while(r2!=null)
												{

													String sp[]=r2.split(",");
													if(Integer.parseInt(sp[3])==i_d)
					{pw.println(sp[0]+","+sp[1]+","+sp[2]+","+sp[3]+","+sp[4]+","+sp[5]+","+sp[6]+","+status);
					System.out.println("Status Updated!! ");
					System.out.println(sp[0]+"  "+sp[1]+"  "+sp[2]+"  "+sp[3]+"  "+sp[4]+"  "+sp[5]+"  "+sp[6]+"  "+status);}
													else
					pw.println(sp[0]+","+sp[1]+","+sp[2]+","+sp[3]+","+sp[4]+","+sp[5]+","+sp[6]+","+sp[7]);
		
													r2=br9.readLine();
												}

													br9.close();
													pw.flush();
													pw.close();
													oldFile.delete();
													File dump=new File("bugdetails.txt");
													newFile.renameTo(dump);
												}
												catch(Exception e)
												{
													System.out.println("Error");
												}
													break;

												case 4:break;
											default:System.out.println("Enter Valid Choice!!");
													break;



										}
									}while(a!=4);	
								//br5.close();
								}
								input=br4.readLine();
							}
							if(flag3==0)
							System.out.println("\nInvalid Admin_Id or password....!!!");
							br4.close();
						case 3:break;
						default:System.out.println("Enter valid Choice!!!");
							break;
					}
				}while(ch!=3);
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not Found!");
			e.printStackTrace();
		}
		catch(NumberFormatException exe)
		{
			System.out.println("Error in input......exitting program!!");
		}
	}
}


								
											
											
									
	
										
											




