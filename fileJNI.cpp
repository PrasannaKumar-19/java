#include "fileJNI.h"
#include <iostream>
#include <fstream>
#include<list>
#include <bits/stdc++.h>
using namespace std;

JNIEXPORT jstring JNICALL Java_fileJNI_create(JNIEnv *env, jobject obj, jstring user, jstring pass, jstring phno) {
			  const char *u = env->GetStringUTFChars(user,NULL);
			  const char *p = env->GetStringUTFChars(pass,NULL);
			  const char *ph = env->GetStringUTFChars(phno,NULL);
			  string res = u;
			  string res1 = p;
			  string res2 = ph;
			  ofstream file(res+".txt");
			  file<<res<<endl<<res1<<endl<<res2;
			  file.close();
			  return env->NewStringUTF("1");
}

JNIEXPORT jstring JNICALL Java_fileJNI_read(JNIEnv *env, jobject obj, jstring user, jstring pass) {
	string arr[3];
	const char *u = env->GetStringUTFChars(user,NULL);
	const char *p = env->GetStringUTFChars(pass,NULL);
	string name = u;
	string pass1 = p;
	string str;
	jstring s;
	ifstream file(name+".txt");
	if(!file){
		s = env->NewStringUTF("1");
	}
	else{
		int n = 0;
		while(getline(file,arr[n])){
			n++;
		}
		file.close();
		string username = arr[0];
		string pa = arr[1];
		string phno = arr[2];
		if(pa.compare(pass1) == 0){
			s = env->NewStringUTF("2");
		}
		else{
			s = env->NewStringUTF("3");
		}
	}
	return s;
}


JNIEXPORT jstring JNICALL Java_fileJNI_forgot(JNIEnv *env, jobject obj, jstring user, jstring phno) {
	string arr[3];
	const char *u = env->GetStringUTFChars(user,NULL);
	const char *ph = env->GetStringUTFChars(phno,NULL);
	string name = u;
	string ph1 = ph;
	jstring s;
	ifstream file(name+".txt");
	if(!file){
		s = env->NewStringUTF("1");
	}
	else{
		int n = 0;
		while(getline(file,arr[n])){
			n++;
		}
		file.close();
		string username = arr[0];
		string pa = arr[1];
		string phn = arr[2];
		if(ph1.compare(phn) == 0){
			string newpass;
			cout<<"enter new password: ";
			cin>>newpass;
			remove(u);
			ofstream file(username+".txt");
			file<<username<<endl<<newpass<<endl<<phn;
			file.close();
			s = env->NewStringUTF("3");
		}
		else{
			s = env->NewStringUTF("2");
		}
	}
	return s;
}

JNIEXPORT jstring JNICALL Java_fileJNI_message(JNIEnv *env, jobject obj, jstring from, jstring to, jstring subject, jstring message) {
	const char *f = env->GetStringUTFChars(from,NULL);
	const char *t = env->GetStringUTFChars(to,NULL);
	const char *s = env->GetStringUTFChars(subject,NULL);
	const char *m = env->GetStringUTFChars(message,NULL);
	string fr = f;
	string tt = t;
	string sub = s;
	string mes = m;
	ifstream cfile(tt+".txt");
	if(!cfile){
		return env->NewStringUTF("2");
	}
	else{
		ofstream file("recieved"+tt+".txt");
		ofstream sfile("sent"+fr+".txt");
		file<<"from:"+fr+" subject:"+sub+" message:"+mes<<endl;
		sfile<<"To: "+tt+" subject: "+sub+" message:"+mes<<endl;
		return env->NewStringUTF("1");
	}
	 
}

JNIEXPORT jstring JNICALL Java_fileJNI_display(JNIEnv *env, jobject obj, jstring user, jstring role) {
	string arr[3];
	const char *u = env->GetStringUTFChars(user,NULL);
	const char *r = env->GetStringUTFChars(role,NULL);
	string name = u;
	string rol = r;
	jstring s;
	ifstream file(rol+name+".txt");
	if(!file){
		s = env->NewStringUTF("1");
	}
	else{
		int n = 0;
		while(getline(file,arr[n])){
			n++;
		}
		int size = *(&arr + 1) - arr;
		for(int i=0; i<size; i++){
		if(arr[i] != "\0"){
			char str[100];
			strcpy(str, arr[i].c_str());
			char *t = strtok(str, " ");
			while (t != NULL) {
				string nn = t;
				if(!nn.find("from:")){
					cout<<i+1<<"."+nn;
				}
				else if(!nn.find("subject:")){
					cout<<" "<<nn<<endl;
				}
				else{
					
				}
				t = strtok(NULL, " ");
				
			}
			//cout<<i+1<<"."+arr[i]<<endl;
		}
	}
		s = env->NewStringUTF("2");
	}
	
		file.close();
	return s;
}

JNIEXPORT jstring JNICALL Java_fileJNI_search(JNIEnv *env, jobject obj, jstring user, jstring word) {
	string arr[10];
	const char *u = env->GetStringUTFChars(user,NULL);
	const char *w = env->GetStringUTFChars(word,NULL);
	string username = u;
	string wor = w; 
	jstring s;
	ifstream file("recieved"+username+".txt");
	int n = 0;
	while(getline(file,arr[n])){
		n++;
	}
	int size = *(&arr+1)-arr;
	int count = 0;
	for(int i=0; i<size; i++){
		if (arr[i].find(wor)){
			if(arr[i] != "\0"){
				cout<<i+1<<"."<<arr[i]<<endl;
				count++;
			}
		}
	}
	if(count>0){
		s = env->NewStringUTF("1");
	}
	else{
		s = env->NewStringUTF("2");
	}
	return s;
}

JNIEXPORT jstring JNICALL Java_fileJNI_select(JNIEnv *env, jobject obj, jstring user, jint id) {
	int n = id;
	string arr[100];
	jstring s;
	const char *u = env->GetStringUTFChars(user,NULL);
	string username = u;
	ifstream file("recieved"+username+".txt");
	int n1 = 0;
	while(getline(file,arr[n1])){
		n1++;
	}
	int size = *(&arr+1)-arr;
	int count = 0;
	for(int i=0; i<size; i++){
		if(arr[i]!="\0" && i==id-1){
			cout<<i+1<<"."<<arr[i]<<endl;
			s = env->NewStringUTF(arr[id-1].c_str());
			count++;
		}
	}
	if(count <= 0){
		s = env->NewStringUTF("2");
	}
	
	return s;
}

JNIEXPORT jstring JNICALL Java_fileJNI_reply(JNIEnv *env, jobject obj, jstring from, jstring to, jstring message, jstring reply) {
	string arr[10];
	const char *f = env->GetStringUTFChars(from,NULL);
	const char *t = env->GetStringUTFChars(to,NULL);
	const char *r = env->GetStringUTFChars(reply,NULL);
	const char *m = env->GetStringUTFChars(message,NULL);
	string fr = f;
	string tt = t;
	string rep = r;
	string mes = m;
	ofstream file("recieved"+tt+".txt");
	ofstream file1("sent"+fr+".txt");
	file<<mes+" reply from "+fr+":"+rep<<endl;
	file1<<mes+" reply to"+tt+":"+rep<<endl;
	return env->NewStringUTF("1");
}
