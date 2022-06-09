import java.util.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
public class fileJNI{
	static {
		System.loadLibrary("file");
	}
	
	public native String create(String user,String pass,String phno);
	public native String read(String user,String pass);
	public native String forgot(String user,String phno);
	public native String message(String from,String to, String subject, String message);
	public native String search(String from,String word);
	public native String select(String user, int id);
	public native String display(String user, String role);
	public native String reply(String from, String to, String message, String reply);
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy ");  
		LocalDateTime now = LocalDateTime.now();
		while(true){	
			System.out.print("\n1.Login 2.signup 3.forgot password\n");
			int n = sc.nextInt();
			if(n==1){
				System.out.print("\nenter username: ");
				String user = sc.next();
				System.out.print("enter password: ");
				String pass = sc.next();
				String ok = new fileJNI().read(user,pass);
				if(ok.equals("1")){
					System.out.print("\nusername doesnot exist\n");
				}
				else if(ok.equals("3")){
					System.out.print("\nincorrect password\n");
				}
				else if(ok.equals("2")){
					System.out.print("\nlogin sucessful\n");
					int login = 0;
					do{
						System.out.print("\n1.Inbox 2.send mail 3.sent 4.logout\n");
						int log = sc.nextInt();
						if(log == 1){
							System.out.print("\nInbox\n");
							String ok1 = new fileJNI().display(user,"recieved");
							int r = 0;
							do{
								System.out.print("\n1.select messages 2.search messages 3.exit: ");
								int sec = sc.nextInt();
								if(sec == 1){
									System.out.print("\nselect messages with id: ");
									int id = sc.nextInt();
									String ok2 = new fileJNI().select(user,id);
									if(ok2.equals("2")){
										System.out.print("nothing found");
									}
									String f = "";
									String[] s1 = ok2.split(" ");
									for(int i=0; i<s1.length; i++){
										String[] s2= s1[i].split(":");
										f = s2[1];
										break;
									}
									System.out.print("\n1.reply 2.go back");
									int h = sc.nextInt();
									if(h==1){
										System.out.print("\nfrom: "+user);
										System.out.print("\nto: "+f);
										sc.nextLine();
										System.out.print("\nenter reply: ");
										String reply = sc.nextLine();
										sc.nextLine();
										String ok3 = new fileJNI().reply(user, f, ok2, reply+" "+dtf.format(now));
									}
									else if(h==2){
										
									}
									else{
										System.out.print("Invalid Input");
									}
								}
								else if(sec == 2){
									System.out.print("Enter a keyword to search: ");
									String  word = sc.next();
									String ok2 = new fileJNI().search(user, word);
									if(!ok2.equals("1")){
										System.out.print(word+" nothing found");
									}
								}
								else if(sec == 3){
									r = 1;
								}
								else{
									System.out.print("invalid input");
								}
							}while(r == 0);
							if(ok1.equals("1")){
									System.out.print("no messages");
							}
							
						}
						else if(log == 2){
							System.out.print("\nfrom: "+user);
							System.out.print("\nto :");
							String to = sc.next();
							System.out.print("\nSubject: ");
							sc.nextLine();
							String subject = sc.nextLine();
							System.out.print("\nmessage: ");
							String message = sc.nextLine();
							sc.nextLine();
							String ok1 = new fileJNI().message(user, to, subject, message+" "+dtf.format(now));
							if(!ok1.equals("1")){
									System.out.print(to+" doesnot exits.message not sent");
							}
							
							
						}
						else if(log == 3){
							System.out.print("\nSents\n");
							String ok1 = new fileJNI().display(user,"sent");
							if(ok1.equals("1")){
									System.out.print("no messages");
							}
							
						}
						else if(log ==4){
							login = 1;
						}
						else{
							System.out.print("\ninvalid input\n");
						}
					}while(login == 0);
				}
			}
			else if(n==2){
				System.out.print("\nenter username: ");
				String user = sc.next();
				System.out.print("\nenter password: ");
				String pass = sc.next();
				System.out.print("\nenter phone number: ");
				String phno = sc.next();
				String ok = new fileJNI().create(user,pass,phno);
				if(ok.equals("1")){
					System.out.print("\nsign up sucessful\n");
				}
			}
			else if(n==3){
				System.out.print("\nenter username: ");
				String user = sc.next();
				System.out.print("\nenter phone number: ");
				String phno = sc.next();
				String ok = new fileJNI().forgot(user,phno);
				if(ok.equals("1")){
					System.out.print("\nusername not exist\n");
				}
				else if(ok.equals("3")){
					System.out.print("\nreset up sucessful\n");
				}
				else{
					System.out.print("\nincorrect phone number\n");
				}
			}
		}
		
	}
}