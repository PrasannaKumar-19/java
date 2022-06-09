import java.io.*;
import java.util.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 
class ne{
	static String user1 = "";
	static void val(String user){
		String path = "C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+user+".txt";
		File file = new File(path);
		try{
			if(file.createNewFile()){
				System.out.print("/nUsername available/n");
				user1 = user;
			}
			else{
					System.out.print("\nalready exist");
					checkuser ck = new checkuser();
					ck.cu(user);

			}
			
		}
		catch(IOException e){
				System.out.print("error occurred in creating");
		}
	}
}
class checkuser{
	static String user1 = "";
	static void cu(String user){
			Scanner sc = new Scanner(System.in);
			System.out.print("\nEnter username again:");
			user = sc.next();
			sc.next();
			ne nee = new ne();
			nee.val(user);
			user1 = user;
	}
}
class write{
	static void wr(String user, String pass, String phno){
		try{
					BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+user+".txt",true));
					bw.write(user);
					bw.newLine();
					bw.write(pass);
					bw.newLine();
					bw.write(phno);
					bw.newLine();
					bw.close();
		}
		catch(IOException e){
					System.out.print("error occurred in writing");
		}
	}
}
class toWriteMessage{
	static int f = 0;
	static void twm(String to, String from, String message){
		File file2 = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+to+".txt");
		if(file2.exists()){
			System.out.print("Message sending....");
			try{
				File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+from+to+".txt");
				BufferedWriter ft = new BufferedWriter(new FileWriter(file,true));
				ft.write(from+": "+message);
				ft.newLine();
				ft.close();
				File file1 = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+to+from+".txt");
				BufferedWriter tf = new BufferedWriter(new FileWriter(file1,true));
				tf.write(from+": "+message);
				tf.newLine();
				System.out.print("\n\n\nMessage Sent");
				tf.close();
				
			}
			catch(IOException e){
				System.out.print("Error sending");
			}
		}
		else{
			System.out.print("Sender doesnot exist");
			f=1;
		}
	}
	
}
class edit{
	static void ed(String user, String pass, String phno){
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+user+".txt"));
				bw.write(user);
				bw.newLine();
				bw.write(pass);
				bw.newLine();
				bw.write(phno);
				bw.newLine();
				System.out.print("Changed sucessfully");
				bw.close();
			}
			catch(IOException e){
				System.out.print("error occurred in writing");
			}
	}
	
	
	
}
class na{
	static String user1 = "";
	static String pass1 = "";
	static String phno1 = "";
	static void lav(String user, String pass){
		ArrayList<String> l = new ArrayList<String>();
		int count = 0;
		String s = "";
		try{
			File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+user+".txt");
			if(file.exists()){
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				while(br.ready()){
					l.add(br.readLine());
				}
				br.close();
				user1 = l.get(0);
				pass1 = l.get(1);
				phno1 = l.get(2);
			}
			else{
				System.out.print(user);
				System.out.print("username doesnot exist");
			}
		}
		catch(IOException e){
			System.out.print("Error in read");
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
class phval1{
	static int c = 0;
	static void pcheck(String phnum){
		Scanner sc = new Scanner(System.in);
		int count = 0;
		for(int i=0; i<phnum.length(); i++){
			if(Character.isLetter(phnum.charAt(i))){
				count++;
			}
		}
		c = count;
	}
}
class phval{
	static String phone = "";
	static void phcheck(String phnum){
		Scanner sc = new Scanner(System.in);
		String s = phnum;
		phval1 n = new phval1();
		n.pcheck(s);
		int ccount = phval1.c;
		while(ccount != 0){
			System.out.print("enter only numbers: ");
			s = sc.next();
			n.pcheck(s);
			ccount = phval1.c;
		}
		phone = s;
	}
	
}
class delete{
	static void del(String user){
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+user+".txt"); 
		file.delete();
	}
}
class displaymsg{
	static void dm(String from, String to){
			File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+from+to+".txt");
			ArrayList<String> ll = new ArrayList<String>();
		try{
			Scanner inputfile = new Scanner(file);
			while(inputfile.hasNextLine()){
				String s = inputfile.nextLine();
				ll.add(s);
			}
			int nn = 1;
				for(int i=ll.size()-1; i>=0; i--){
						System.out.println(nn+" "+ll.get(i));
						nn++;
				}
		
		}
		catch(IOException e){
					System.out.print("no messages available");
		}
	}
}
class search{
	static void ser(String ul){
		Scanner sc	= new Scanner(System.in);
			ArrayList<String> l = new ArrayList<String>();
			try{
				int h = 1;
				File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+ul+".txt");
				Scanner inputfile = new Scanner(file);
				while(inputfile.hasNextLine()){
					String s = inputfile.nextLine();
						l.add(Integer.toString(h));
						l.add(s);
						h++;
				}
				System.out.print("Enter something to search: ");
				String s = sc.next();
				
				for(int i=0; i<l.size(); i++){
					if(l.get(i).contains(s)){
						System.out.println(l.get(i-1)+"."+l.get(i));
					}
				}
				System.out.print("Select message with id: ");
				String s1 = sc.next();
				String message = "";
				int pos = 0;
				if(l.contains(s1)){
					for(int i=0; i<l.size(); i++){
						if(l.get(i).equals(s1)){
							pos = i+1;
							message = l.get(i+1);
						}
					}
				}
				System.out.print(message);
				int gm = 0;
				do{
					System.out.print("\n1.reply 2.goback");
					int y = sc.nextInt();
					if(y==1){
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy ");  
						LocalDateTime now = LocalDateTime.now(); 
						System.out.print("enter message: ");
						sc.nextLine();
						String mes = sc.nextLine();
						String sent = message+",reply: "+mes+" "+" "+dtf.format(now);
						String recieve = message+",reply from "+ul+" "+mes+" "+" "+dtf.format(now);
						for(int i=0; i<l.size(); i++){
							if(sent.contains(l.get(i))){
								l.set(i,sent) ;
							}
						}
						BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\sent"+ul+".txt",true));
						for(int i=0; i<l.size(); i++){
							if(i%2!=0){
								bw.write(l.get(i));
								bw.newLine();
								bw.close();
							}
						}
						String name = "";
						for(int i=0; i<message.length(); i++){
							if(message.charAt(i)!=','){
								name+=message.charAt(i);
							}
							else{
								break;
							}
						}
						File file2 = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+name+".txt");
						BufferedWriter bb = new BufferedWriter(new FileWriter(file2,true));
							bb.write(recieve);
							bb.newLine();
							bb.close();
						
					}
					else if(y==2){
						gm=1;
					}
					
				}while(gm==0);
				
				
			}
			catch(Exception e){
					System.out.print("no messages available");
			}
		
	}
	
}
class inbox{
	static void in(String u1){
		Scanner sc = new Scanner(System.in);
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+u1+".txt");
			ArrayList<String> ll = new ArrayList<String>();
			String name = u1;
		try{
			Scanner inputfile = new Scanner(file);
			while(inputfile.hasNextLine()){
				String s = inputfile.nextLine();
				ll.add(s);
			}
			int nn = 1;
			for(int i=ll.size()-1; i>=0; i--){
				System.out.print(nn+". ");
				String ss = ll.get(i);
				String[] s2 = ss.split(",");
				int flag = 0;
					for(int j=0; j<s2.length; j++){
						if(s2[j].contains("reply")){
							flag = 1;
						}
					}
					if(flag == 1){
						if(s2[s2.length-1] == "*"){
							System.out.print(s2[0]+" || "+s2[s2.length-3]);
						}
						else{
							System.out.print(s2[0]+" || "+s2[s2.length-1]);
						}
					}
					else{
						System.out.print(s2[0]+" || "+s2[1]);
					}
				nn++;
				System.out.println();
			}
			nn=0;
			int lo = 0;
			do{
				System.out.print("\n1.Select Message 2.search messages 3.goback: ");
				int kl = sc.nextInt();
				if(kl==1){
					System.out.print("\nselect message using id: ");
					int star = sc.nextInt();
					seen sn = new seen();
					sn.see(u1,star);
					String s3 = ll.get(star-1);
					String [] s4 = s3.split(",");
					for(int i=0; i<s4.length; i++){
						if(!s4[i].contains("*")){
							System.out.print(s4[i]+"\n\n");
						}
					}
					System.out.print("1.reply 2.goback");
					int g = sc.nextInt();
					if(g == 1){
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy ");  
						LocalDateTime now = LocalDateTime.now(); 
						System.out.print("enter message: ");
						sc.nextLine();
						String st = sc.nextLine();
						String s5 = ll.get(star-1)+",reply from "+u1+": "+st+" "+dtf.format(now);
						ll.set(star-1,s5);
						BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\sent"+u1+".txt",true));
						for(int i=0; i<ll.size(); i++){
							bw.write(ll.get(i));
							bw.newLine();
							bw.close();
						}
						senwrite sop = new senwrite();
						sop.senti(name,star);
					}
					else if(g==2){
						lo=0;
					}
					else{
						lo=0;
					}
				}
				else if(kl==2){
					System.out.print("Enter keyword Search: ");
					String searc = sc.next();
					for(int i=ll.size()-1; i>=0; i--){
						String ss = ll.get(i);
						if(ss.contains(searc)){
							String[] s2 = ss.split(",");
							int flag = 0;
								for(int j=0; j<s2.length; j++){
									if(s2[j].contains("reply")){
										flag = 1;
									}
								}
								if(flag == 1){
									System.out.print(i+1+"."+s2[0]+" || "+s2[s2.length-1]);
								}
								else{
									System.out.print(i+1+"."+s2[0]+" || "+s2[1]);
								}
						}
						System.out.println();
					}
					System.out.print("\nselect message using id: ");
					int star = sc.nextInt();
					seen sn = new seen();
					sn.see(u1,star);
					String s3 = ll.get(star-1);
					String [] s4 = s3.split(",");
					for(int i=0; i<s4.length; i++){
						if(!s4[i].contains("*")){
							System.out.print(s4[i]+"\n\n");
						}
					}
					System.out.print("1.reply 2.goback: ");
					int g = sc.nextInt();
					if(g == 1){
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy ");  
						LocalDateTime now = LocalDateTime.now(); 
						System.out.print("enter message: ");
						sc.nextLine();
						String st = sc.nextLine();
						String s5 = ll.get(star-1)+",reply from "+u1+": "+st+" "+dtf.format(now);
						ll.set(star-1,s5);
						BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\sent"+u1+".txt",true));
						for(int i=0; i<ll.size(); i++){
							bw.write(ll.get(i));
							bw.newLine();
							bw.close();
						}
						senwrite sop = new senwrite();
						sop.senti(name,star);
					}
					else if(g==2){
						lo=0;
					}
					else{
						lo=0;
					}
				}
				else if(kl==3){
					lo=1;
				}
			}while(lo==0);
		}
	
				catch(IOException e){
							System.out.print("no messages available");
				}
	}
}
class senwrite{
	static void senti(String user, int star){
		Scanner sc = new Scanner(System.in);
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\sent"+user+".txt");
		ArrayList<String> l = new ArrayList<String>();
		try{
			Scanner inputfile = new Scanner(file);
			while(inputfile.hasNextLine()){
				String s = inputfile.nextLine();
				l.add(s);
			}
			//String ss = l.get(l.size()-star);
			String ss = l.get(l.size()-1);
			String s2 = "";
			String s3 = "";
			/*for(int i=0; i<ss.length(); i++){
				if(ss.charAt(i)!=','){
					s2+=ss.charAt(i);
				}
				else{
					break;
				}
				
				
			}*/
			for(int i=ss.length()-1; i>=0; i--){
				if(ss.charAt(i)!=',' && ss.charAt(i)!='*'){
					s3+=ss.charAt(i);
				}
				else{
					break;
				}
			}
			String s5 = "";
			if(s3.contains("reply")){
				String[] s4 = s3.split(" ");
				s5 = s4[2];
				String s6 = "";
				for(int i=0; i<s5.length(); i++){
					s6+=s5.charAt(i);
				}
				s2=s6;
			}
			else{
				
				for(int i=0; i<ss.length(); i++){
					if(ss.charAt(i)!=','){
						s2+=ss.charAt(i);
					}
					else{
						break;
					}
				
				
				}
			}
			
			File file2 = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+s2+".txt");
			BufferedWriter br = new BufferedWriter(new FileWriter(file2,true));
			br.write(l.get(l.size()-1));
			br.newLine();
			br.close();
			
		}
		catch(IOException e){
					System.out.print("no messages available");
		}
	}
	
}
class sent{
	static void s(String username, String to, String message){
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\sent"+username+".txt");
		try{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy ");  
			LocalDateTime now = LocalDateTime.now();        
			BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
			br.write("from "+username+" to "+to+" Subject:"+message+" "+dtf.format(now));
			br.newLine();
			br.close();
		}
		catch(IOException e){
				System.out.print("error occurred in creating");
		}
	}
}
class recieve{
	static void r(String username,  String to, String message,String subject){
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+to+".txt");
		try{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");  
			LocalDateTime now = LocalDateTime.now();        
			BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
			br.write(username+",subject:"+subject+",content:"+message+" "+dtf.format(now));
			br.newLine();
			br.close();
		}
		catch(IOException e){
				System.out.print("error occurred in creating");
		}
	}
	
}
class seen{
	static void see(String to, int n){
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+to+".txt");
		ArrayList<String> l = new ArrayList<String>();
		try{
			Scanner inputfile = new Scanner(file);
			while(inputfile.hasNextLine()){
				String s = inputfile.nextLine();
				l.add(s);
			}
			String seenn = ",*";
			if(!l.get(l.size()-n).contains("*") || l.get(l.size()-n).contains("reply")){
				String s = l.get(l.size()-n);
				l.set(l.size()-n,s+seenn);
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(int i=0; i<l.size(); i++){
					bw.write(l.get(i));
					bw.newLine();
					bw.close();
				}
			}
				
		}
		catch(IOException e){
				System.out.print("error occurred in creating");
				
		}
		
	}
}
class del{
	static void dm(String username){
		Scanner sc = new Scanner(System.in);
		File file = new File("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\recieved"+username+".txt");
		ArrayList<String> ll = new ArrayList<String>();
		try{
			Scanner inputfile = new Scanner(file);
			while(inputfile.hasNextLine()){
				String s = inputfile.nextLine();
				ll.add(s);
			}
			int nn = ll.size();
				for(int i=0; i<ll.size(); i++){
						System.out.println(i+1+" "+ll.get(i));
				}
				System.out.print("enter line number to delete messge: ");
				int n = sc.nextInt();
				BufferedWriter bb = new BufferedWriter(new FileWriter("C:\\Users\\prasa\\OneDrive\\Desktop\\files\\"+username+"trash.txt"));
				bb.write(ll.get(n-1));
				bb.newLine();
				bb.close();
				ll.remove(n-1);
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(int i=0; i<ll.size(); i++){
					bw.write(ll.get(i));
					bw.newLine();
					bw.close();
					System.out.println(ll.get(i));
				}
				System.out.print("Message deleted sucessfully");
		
		}
		catch(IOException e){
					System.out.print("no messages available");
		}
	}
		
}
public class MailManagement{
	
	public static void main(String[] args){
		
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.print("\n 1.Login 2.signup 3.forgot password");
				System.out.print("\n enter process: ");
				int n = sc.nextInt();
				if(n==2){
					System.out.print("\nEnter phone number: ");
/*check phno*/
					String phno = sc.next();
					phval g = new phval();
					g.phcheck(phno);
					phno = phval.phone;
/*phno checked*/	
					System.out.print("\nenter username: ");
					String username = sc.next();
/*check username*/
					ne nee = new ne();
					nee.val(username);
					username = ne.user1;
/*username checked*/
					System.out.print("\nenter password: ");
					String password = sc.next();
/*check password*/
					passent pnext = new passent();
					pnext.check(password);
					password = passent.pass2;
/*password checked*/
/*writing file*/
					write no = new write();
					no.wr(username,password,phno);
					System.out.print("Details added sucessfully");
/*file wrote*/				
				}
				else if(n==1){
					System.out.print("\nenter username: ");
					String username = sc.next();
					System.out.print("\nenter password :");
					String password = sc.next();
					passent pnext = new passent();
/*check password*/					
					pnext.check(password);
					password = passent.pass2;
/*reading file for login detains*/
					na aa = new na();
					aa.lav(username,password);
					String u = na.user1;
					String pp = na.pass1;
					if(u.equals(username)){
						if(pp.equals(password)){
							System.out.print("\nlogin sucessful");
							int login = 1;
							do{
								System.out.print("\n1.inbox 2.send message 3.sent 4.settings 5.logout 6.delete account 7.Trash");
								System.out.print("\nnavigate to: ");
								int lin = sc.nextInt();
								if(lin == 1){
									int log = 0;
									do{
										inbox io = new inbox();
										io.in(username);
										System.out.print("\n1.Delete message 2.go back: ");
										int rep = sc.nextInt();
										if(rep ==1){
											del d = new del();
											d.dm(username);
											
										}
										else if(rep==2){
											log = 1;
										}
									}while(log==0);
								}
								else if(lin==2){
									System.out.print("\n*****Send Messages*****");
									System.out.print("\nFrom:"+username);
									System.out.print("\nto: ");
									String to = sc.next();
									int mesnum = 1;
									do{
										System.out.print("\nsubject: ");
										String subject = sc.next();
										System.out.print("\nEnter message: ");
										sc.nextLine();
										String me = sc.nextLine();
										sent sen = new sent();
										recieve res = new recieve();
										sen.s(username,to,me);
										res	.r(username,to,me,subject);
										System.out.print("\nto send again press y to terminate press n: ");
										char ms = sc.next().charAt(0);
										if(ms=='n' || ms=='N'){
											mesnum = 0;
										}
										else{
											continue;
										}
									}while(mesnum!=0);
								}
								else if(lin==3){
									System.out.print("\nmessages:\n");
									displaymsg dd = new displaymsg();
									dd.dm("sent",username);
									
								}
								else if(lin==4){
									System.out.print("\n*****settings*****");
									int setlogin = 1;
									do{
										System.out.print("\n1.change password 2.change phone number 3.go back");
										System.out.print("\n navigate to :");
										int ls = sc.nextInt();
										if(ls==1){
											System.out.print("\n****password changing****");
											System.out.print("enter phone number: ");
											String ph1 = sc.next();
											phval g1 = new phval();
											g1.phcheck(ph1);
											ph1 = phval.phone;
											System.out.print("enter new password: ");
											String chpass = sc.next();
											passent pnex = new passent();
											pnex.check(chpass);
											chpass = passent.pass2;
											System.out.print("Re-enter password: ");
											String chpass1 = sc.next();
											while(!chpass1.equals(chpass)){
												System.out.print("passwords does not match enter again: ");
												chpass1 = sc.next();
											}
/*checking credentials*/					na conn = new na();
											conn.lav(username,chpass);
/*overwriting file*/
												edit y = new edit();
												y.ed(username,chpass,ph1);
										}
										else if(ls==2){
											System.out.print("\n****phone number changing****");
											System.out.print("\n Enter new phone number: ");
											String ph2 = sc.next();
											phval g2 = new phval();
											g2.phcheck(ph2);
											ph2 = phval.phone;
											edit b = new edit();
											b.ed(username,password,ph2);
										}
										else if(ls==3){
											setlogin=0;
										}
										
									}while(setlogin!=0);
									
									continue;
								}
								else if(lin==5){
									login=0;
								}
								else if(lin==6){
											System.out.print("\n****deleting account****");
											System.out.print("\npress y to delete and n to go back: ");
											char a = sc.next().charAt(0);
									if(a=='Y' || a=='y'){
												delete d = new delete();
												d.del(username);
												login=0;
												
									}
									else{
										System.out.print("\noperation cancelled");
										}
								}
								else if(lin==7){
									System.out.print("\n***trash***\n");
									displaymsg ddm = new displaymsg();
									ddm.dm(username,"trash");
								}
								else{
									System.out.print("invalid input");
									continue;
								}
							}while(login!=0);
						}
						else{
							System.out.print("incorrect password");
						}
					}
				}
				else if(n==3){
					na con = new na();
					System.out.print("Enter username:");
					String username = sc.next();
					System.out.print("Enter Phone number: ");
					String phno = sc.next();
					phval g = new phval();
					g.phcheck(phno);
					phno = g.phone;
					System.out.print("enter new password: ");
					String password = sc.next();
					passent pnext = new passent();
					pnext.check(password);
					password = passent.pass2;
					
					System.out.print("Re-enter password: ");
					String password1 = sc.next();
					while(!password1.equals(password)){
						System.out.print("passwords does not match enter again: ");
						password1 = sc.next();
					}
/*checking credentials*/
					con.lav(username,password);
					if(username.equals(con.user1) && phno.equals(con.phno1)){
/*overwriting file*/
						edit nee = new edit();
						nee.ed(username,password,phno);
					}
					else if(password.equals(con.pass1)){
						System.out.print(password+" is your correct password login again");
					}
					else{
						System.out.print("Incorrect phone number");
					}
				}
				else{
					
					System.out.print("invalid input");
					continue;
				}
			}
	}
}