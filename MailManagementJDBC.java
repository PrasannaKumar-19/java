import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.io.IOException;
import java.security.*;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import de.taimos.totp.TOTP;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
class PickAFile {
	static String path = "";
    static void pf(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "txt", "xml", "java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
			
			File file = chooser.getSelectedFile();
			String fullPath = file.getAbsolutePath();
			System.out.println(fullPath);
			path = fullPath;
        }
    }
}
class openFile{
	static void op(String path){
		try{
			Process p;
			String command = "rundll32 url.dll,FileProtocolHandler \""+ path +"\"";
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
class Scan{
	static Scanner sc = null;
	static void scan(){
		sc = new Scanner(System.in);
	}
}
class Connect{
	static	Connection c = null;
	static  Statement stmt = null;
	static void con(){
	   try{
		 //Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "prasanna");
         stmt = c.createStatement();
	   } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }	
	}
}
class close{
	static void cl(){
		try{
			Connect.stmt.close();
			Connect.c.close();
		}
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
      }	
	}
}
class Create{
	static void cr(){
		
		
		try {
			String sql = "CREATE TABLE login " +
			"(Username varchar2(10) PRIMARY KEY NOT NULL,"+
			"Password varchar2(15) NOT NULL,"+
			"Phno bigint(10) NOT NULL)";
			Connect.stmt.executeUpdate(sql);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}	
		System.out.print("Table created sucessfully");
	}
	
}
class insert{
	static void in(String user, String pass, long phno, String key, String email){
		
		try {
			String sql = "INSERT INTO login VALUES('"+user+"','"+pass+"',"+phno+",'"+key+"'+'"+email+"')";
			Connect.stmt.executeUpdate(sql);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Signup sucessful");
	}
	
}
class result{
	static String username = "";
	static String password = "";
	static long phno = 0;
	static String key = "";
	static String email = "";
	static void re(String user){
		
		try {
			ResultSet result = Connect.stmt.executeQuery("SELECT * FROM login where Username = '"+user+"';");
			while (result.next()){
				username = result.getString("Username");
				password = result.getString("Password");
				phno = result.getLong("Phno");
				key = result.getString("authkey");
				email = result.getString("otpmail");
			}
			result.close();
		} 
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
}
class update{
	static void up(String p, String ans, String user){
		
		try{
			String sql = "UPDATE login set "+p+" = '"+ans+"' where Username = '"+user+"';";
	 		Connect.stmt.execute(sql);
		}
		catch(Exception e){
			System.err.print(e.getClass().getName() + ":"+ e.getMessage());
			System.exit(0);
		}
		System.out.print(p+" Updated Sucessfully");
	}
}
class updatePh{
	static void uph(String p, long ans, String user){
		
		try{
			String sql = "UPDATE login set "+p+" = "+ans+" where Username = '"+user+"';";
	 		Connect.stmt.execute(sql);
		}
		catch(Exception e){
			System.err.print(e.getClass().getName() + ":"+ e.getMessage());
			System.exit(0);
		}
		System.out.print(p+" Updated Sucessfully");
	}
}
class settings{
	static void sett(String user){
		Scan ss = new Scan();
		ss.scan();
		int n = 0;
		do{
					  System.out.print("\n1.change username 2.change password 3.change phone number 4.exit");
					  System.out.print("\nnavigate to: ");
					  int n1 = Scan.sc.nextInt();
					  if(n1==1){
						  System.out.print("\n Changing username \n");
						  System.out.print("\n Enter new username: ");
						  String newUsername = Scan.sc.next();
						  update u = new update();
						  u.up("Username",newUsername,user);
					  }
					  else if(n1==2){
						  System.out.print("\n Changing password \n");
						  System.out.print("\n Enter new password: ");
						  String newPassword = Scan.sc.next();
						  update u = new update();
						  u.up("Password",newPassword,user);
					  }
					  else if(n1==3){
						  System.out.print("\n Changing phone number \n");
						  System.out.print("\n Enter new phone number: ");
						  long newPhno = Scan.sc.nextLong();
						  updatePh u = new updatePh();
						  u.uph("Phno",newPhno,user);
					  }
					  else{
						  n=1;
					  }
		}while(n==0);
	}
}
class delete{
	static void del(String user){
		try{
			String sql = "DELETE FROM login where Username = '"+user+"';";
	 		Connect.stmt.execute(sql);
		}
		catch(Exception e){
			System.err.print(e.getClass().getName() + ":"+ e.getMessage());
			System.exit(0);
		}
		System.out.print("Account Deleted Sucessfully");
	}
}
class inbox{
	static int rsmg = 0; 
	static void in(String user){
		try {
			ResultSet result = Connect.stmt.executeQuery("SELECT * FROM message where to_user = '"+user+"';");
			
			while (result.next()){
				rsmg = Integer.parseInt(result.getString("msg_id"));
				int count = 0;
				Connect co = new Connect();
				co.con();
				ResultSet result1 = Connect.stmt.executeQuery("SELECT from_user, message, doe FROM reply where msg_id = "+rsmg+" and to_user = '"+user+"' order by doe desc limit 1");
				int count1 = 0; 
				if(result1.next()){
					System.out.print("\n"+rsmg+". from"+result1.getString("from_user")+" reply:"+result1.getString("message")+" "+result1.getString("doe")+"\n");
				}
				else{
						System.out.print("\n"+result.getString("msg_id")+ "from:"+result.getString("from_user")+" Subject:"+result.getString("subject"));
				}
			}
			ResultSet result3 = Connect.stmt.executeQuery("SELECT * FROM reply where to_user = '"+user+"' order by doe desc;");
			while(result3.next()){
				System.out.print("\n"+result3.getString("msg_id")+" reply from :" +result3.getString("from_user")+" message:"+result3.getString("message"));
			}
		} 
		catch ( Exception e ) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
}
class sendMessage{
	static void sm(String from, String to, String subject, String message){
		try {
			String sql = "INSERT INTO message (from_user,to_user,subject,message,attachment)VALUES('"+from+"','"+to+"','"+subject+"','"+message+"','"+PickAFile.path+"')";
			Connect.stmt.executeUpdate(sql);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.print("Record Inserted");
	}
}
class search{
	static void se(String s, String user){
		try {
			ResultSet result = Connect.stmt.executeQuery("SELECT * FROM message where from_user LIKE '%"+s+"%' OR to_user LIKE '%"+s+"%' OR message LIKE '%"+s+"%' OR subject LIKE '%"+s+"%' OR attachment LIKE '%"+s+"%' AND to_user = '"+user+"';");
			while (result.next()){
				System.out.print("\n"+result.getString("msg_id")+". from: "+result.getString("from_user")+" Subject:"+result.getString("subject")+" "+result.getString("dos")+"\n");
			}
			result.close();
		} 
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
}
class reply{
	static String from = "";
	static String to = "";
	static void r(String message, String user){
		try{
			ResultSet result = Connect.stmt.executeQuery("SELECT from_user, to_user FROM reply where msg_id = "+select.id+" ORDER BY doe DESC limit 1;");
			int count = 0;
			if(result.next()){
				count++;
				from = result.getString("from_user");
				to = result.getString("to_user");
				count++;
			}
			if(user == from){
				String sql1 = "UPDATE message set from_user = '"+from+"', to_user = '"+to+"' where msg_id = "+select.id+"";
				Connect.stmt.execute(sql1);
			}
			else{
				String sql1 = "UPDATE message set from_user = '"+to+"', to_user = '"+from+"' where msg_id = "+select.id+"";
				Connect.stmt.execute(sql1);
			}
			result.close();
			if(count!=0){
				String sql = "INSERT INTO reply(from_user, to_user, message, msg_id) VALUES('"+to+"','"+from+"','"+message+"',"+select.id+")";
				Connect.stmt.executeUpdate(sql);
			}
			else{
				String sql = "INSERT INTO reply(from_user, to_user, message, msg_id) VALUES('"+select.to+"','"+select.from+"','"+message+"',"+select.id+")";
				Connect.stmt.executeUpdate(sql);
			}
		} 
		catch ( Exception e ) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
}
class sent{
	static void se(String user){
		try {
			ResultSet result = Connect.stmt.executeQuery("SELECT * FROM message where from_user = '"+user+"';");
			while (result.next()){
				System.out.print("\n"+result.getString("msg_id")+". to: "+result.getString("to_user")+" Subject:"+result.getString("subject")+" "+result.getString("dos")+"\n");
			}
			result.close();
		} 
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
}
class select{
	static int id;
	static String to = "";
	static String from = "";
	
	static void sel(int sel){
		try {
			ResultSet result = Connect.stmt.executeQuery("SELECT * FROM message where msg_id = "+sel+";");
			result.next();
			System.out.print("\n"+result.getString("msg_id")+". from: "+result.getString("from_user")+"\n Subject:"+result.getString("subject")+"\n Content:"+result.getString("message")+"\n"+result.getString("dos")+"\n");
			id = Integer.parseInt(result.getString("msg_id"));
			to = result.getString("to_user");
			from = result.getString("from_user");
			Connect co = new Connect();
			co.con();
			ResultSet result1 = Connect.stmt.executeQuery("SELECT * FROM reply where msg_id = "+sel+";");
			while(result1.next()){
				System.out.println("reply from: "+result1.getString("from_user")+"|| message: "+result1.getString("message"));
			}
			ResultSet result7 = Connect.stmt.executeQuery("SELECT attachment FROM message where msg_id = "+sel+";");
			if(result7.next()){
				System.out.print("\nattachment: "+result7.getString("attachment"));
				System.out.print("to open attachment press 1 :");
				int one = Scan.sc.nextInt();
				if(one == 1){
					openFile oo = new openFile();
					oo.op(result7.getString("attachment"));
				}
				else{
					
				}
			}
		} 
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
}
class passe{
				static int	chcount = 0;
				static int	digcount = 0;
				static int	spcount = 0;
				static int	wpcount = 0;
			void val(String pass){	
				for(int i=0; i<pass.length(); i++){	
						if(Character.isDigit(pass.charAt(i))){
							digcount++;
						}
						else if(Character.isWhitespace(pass.charAt(i))){
							wpcount++;
						}
						else if(Character.isLetter(pass.charAt(i))){
							chcount++;
						}
						else{
							spcount++;
						}
				}
			}
}
class passent{
		static String pass2;
		void check(String pass3){
				String pass1 = pass3;
				Scanner sc = new Scanner(System.in);
				passe passval = new passe();
				passval.val(pass1);
				while(pass1.length()<8 /*&& p.length()>14*/){
					
					System.out.print("Password length should be atleast 8\n");
					System.out.print("enter password again: ");
					pass1 = sc.next().trim();
					System.out.print("\n");	
				}
				while(pass1.length()>14){
						System.out.print("Password length should be maximum 14\n");
						System.out.print("enter password again: ");
						pass1 = sc.next().trim();
						System.out.print("\n");
				}
				int wcount = passe.wpcount;
				int scount = passe.spcount;
				int dcount = passe.digcount;
				while(wcount > 0){
					System.out.print("Password should not contain spaces\n");
					System.out.print("enter password again: ");
					pass1 = sc.next().trim();
					System.out.print("\n");
					passval.val(pass1);
					wcount = passe.wpcount;
				}
				while(scount < 1){
					System.out.print("Password should contain one special character\n");
					System.out.print("enter password again: ");
					pass1 = sc.next().trim();
					System.out.print("\n");
					passval.val(pass1);
					scount = passe.spcount;
				}
				while(dcount < 1){
					System.out.print("Password should contain one number\n");
					System.out.print("enter password again: ");
					pass1 = sc.next().trim();
					System.out.print("\n");
					passval.val(pass1);
					dcount = passe.digcount;
				}
				
				pass2 = pass1;
			
		}
	
}
class mail {
    static final String USERNAME = "otpgenerate15@gmail.com";
    static final String PASSWORD = "prasanna@2002";
    public void sendMail(String mailFrom, String mailTo, String mailSubject,
            String mailText) throws Exception {
        Properties config = createConfiguration();
        Session session = Session.getInstance(config, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        mail.USERNAME,
                        mail.PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setSentDate(new Date());
        message.setFrom(new InternetAddress(mailFrom));
        message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(mailTo));
        message.setSubject(mailSubject);
        message.setText(mailText);
        Transport.send(message);
    }

    public Properties createConfiguration() {
        return new Properties() {
            {
                put("mail.smtp.auth", "true");
                put("mail.smtp.host", "smtp.gmail.com");
                put("mail.smtp.port", "587");
                put("mail.smtp.starttls.enable", "true");
                put("mail.smtp.ssl.protocols", "TLSv1.1");//SSLv2Hello, SSLv3, TLSv1, TLSv1.1, TLSv1.2
            }
        };
    }
	public static void mainn(int otp){
		try{
			String mailFrom = "otpgenerate15@gmail.com";
			String mailTo = result.email;
			String mailSubject = "One Time Password";
			String mailText="your one time password for MailManagement login is "+otp;
			
			mail gmail = new mail();
			gmail.sendMail(mailFrom, mailTo, mailSubject, mailText);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
}
public class MailmanagementJDBC {
	public static String generateSecretKey() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    return base32.encodeToString(bytes);
	}
	public static String getTOTPCode() {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(result.key);
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}
  public static void main( String args[] ) {
	  
		//insert inn = new insert();
		  //inn.in();
		  System.out.print("\nMAIL MANAGEMENT\n");
		while(true){
				Connect conn = new Connect();
				conn.con();
				Scan sca = new Scan();
				sca.scan();
			  System.out.print("\n 1.login 2.signup 3.forgot password\n");
			  int ss = Scan.sc.nextInt();
			  if(ss==1){
				  result ree = new result();
				  System.out.print("username: ");
				  String user = Scan.sc.next();
				  ree.re(user);
				  System.out.print("password: ");
				  String pass = Scan.sc.next();
				  passe p = new passe();
				  p.val(pass);
				  passent p1 = new passent();
				  p1.check(pass);
				  pass = passent.pass2;
				  if(pass.equals(result.password)){
					  int authent = 0;
					  do{
						  System.out.print("1.Login by Google authenticator 2.Login by otp sent to given email : ");
						  int auu = Scan.sc.nextInt();
						  if(auu==1){
							  System.out.print("enter 6 digit authentication code: ");
							  int auth = Scan.sc.nextInt();
							  int crauth = 0;
							  int authentication = 0;
								do{
									String code = getTOTPCode();
									String lastCode = null;
									if (!code.equals(lastCode)) {
										crauth = Integer.parseInt(code);
									}
									lastCode = code;
									if(auth == crauth){
										authentication = 1;
										authent = 1;
									}
									else{
										System.out.print("Enter correct code: ");
										auth = Scan.sc.nextInt();
									}
									try {
										Thread.sleep(1000);
									} catch (Exception e) {e.printStackTrace();};
								}while(authentication==0);
						  }
						  else if(auu == 2){
							  mail Mail = new mail();
							  String code = getTOTPCode();
							  int n = Integer.parseInt(code);
							  Mail.mainn(n);
							  System.out.print("\nOtp is sent to your email");
							  System.out.print("\nenter your otp: ");
							  int otp = Scan.sc.nextInt();
							  if(n == otp){
								  authent=1;
							  }
							  else{
								  System.out.print("incorrect otp");
							  }
						  }
					  }while(authent==0);
						System.out.print("\nLogin sucessful");
						int login = 0;
						do{
							System.out.print("\n1.inbox 2.send message 3.Sent box 4.Settings 5.logout 6.Delete account\n");
							System.out.print("\n navigate to: ");
							int n = Scan.sc.nextInt();
							if(n==1){
								System.out.print("\nINBOX");
								inbox inn = new inbox();
								inn.in(user);
								int box = 0;
								do{
									System.out.print("\n1.Search message 2.Select message 3.go back: ");
									int mes = Scan.sc.nextInt();
									if(mes == 1){
										System.out.print("Enter a keyword to search: ");
										String ch = Scan.sc.nextLine();
										Scan.sc.nextLine();
										search s2 = new search();
										s2.se(ch,user);
										System.out.print("Select message with id: ");
										int sm = Scan.sc.nextInt();
										select see = new select();
										see.sel(sm);
										System.out.print("1.reply 2.goback: ");
										int r = Scan.sc.nextInt();
										if (r==1){
											System.out.print("enter message: ");
											Scan.sc.nextLine();
											String message = Scan.sc.nextLine();
											
											reply rr = new reply();
											rr.r(message,user);
										}
										else if(r==2){
											box=1;
										}
									}
									else if(mes == 2){
										System.out.print("Select message with id: ");
										int sm = Scan.sc.nextInt();
										select see = new select();
										see.sel(sm);
										System.out.print("1.reply 2.goback: ");
										int r = Scan.sc.nextInt();
										if (r==1){
											System.out.print("enter message: ");
											Scan.sc.nextLine();
											String message = Scan.sc.nextLine();
											
											reply rr = new reply();
											rr.r(message,user);
										}
										else if(r==2){
											box=1;
										}
									}
									else{
										box = 1;
									}
								}while(box==0);
								
							}	
							else if(n==2){
								System.out.print("\nSend messages\n");
								System.out.print("\nTo: ");
								String to = Scan.sc.next();
								System.out.print("\nSubject: ");
								Scan.sc.nextLine();
								String subject = Scan.sc.nextLine();
								System.out.print("\nMessage: ");
								String message = Scan.sc.nextLine();
								System.out.print("1 to add attachment 2 to exit : ");
								int yes = Scan.sc.nextInt();
								if(yes==1){
									PickAFile paf = new PickAFile();
									paf.pf();
								}
								else{
									PickAFile.path = "";
								}
								sendMessage sM = new sendMessage();
								sM.sm(user,to,subject,message);
							}
							else if(n==3){
								System.out.print("\nSent\n");
								sent sn = new sent();
								sn.se(user);
							}
							else if(n==4){
								System.out.print("\nSettings\n");
								settings set = new settings();
								set.sett(user);
							}
							else if(n==5){
								login=1;
							}
							else if(n==6){
							  System.out.print("\nDeleting account\n");
							  System.out.print("yes to delete your account no to terminate: ");
							  String deleteacc = Scan.sc.next();
							  if(deleteacc.equals("yes")){
									delete dell = new delete();
									dell.del(user);
							  }
							  else{
								  System.out.print("operation terminated");
							  }
							}
							else{
								System.out.print("Invalid input");
							}							
						}while(login==0);
				  }
				  else{
					  System.out.print("Incorrect username or password");
				  }
				}
			  else if(ss==2){
				  System.out.print("\nenter username: ");
				  String user = Scan.sc.next();
				  System.out.print("\nenter phone number: ");
				  long ph = Scan.sc.nextLong();
				  System.out.print("\nenter your password: ");
				  String pass = Scan.sc.next();
				  passe p = new passe();
				  p.val(pass);
				  passent p1 = new passent();
				  p1.check(pass);
				  pass = passent.pass2;
				  System.out.print("Enter email: ");
				  String email = Scan.sc.next();
				  insert inn = new insert();
				  String s = generateSecretKey();
				  System.out.println("your google authenticator key is: "+s);
				  inn.in(user,pass,ph,s,email);
				  
			  }
			  else if(ss==3){
				  System.out.print("\nenter username: ");
				  String user = Scan.sc.next();
				  System.out.print("\nenter phone number: ");
				  long ph = Scan.sc.nextLong();
				  result res = new result();
				  res.re(user);
					if(user.equals(result.username)){
						if(ph == result.phno){
						  System.out.print("Enter new password");
						  String newpass = Scan.sc.next();
						  passe p = new passe();
							p.val(newpass);
							passent p1 = new passent();
							p1.check(newpass);
							newpass = passent.pass2;
							update u = new update();
							u.up("Password",newpass,user);
						}
						else{
						  System.out.print("wrong phone number");
						}
					}
					else{
					  System.out.print("incorrect username");
					}
				}
				close clo = new close();
				clo.cl();
		}
		
		

   }


}