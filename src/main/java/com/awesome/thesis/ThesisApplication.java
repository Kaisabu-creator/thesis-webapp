package com.awesome.thesis;

import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class ThesisApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ThesisApplication.class, args);
//	}
//
//}


@SpringBootApplication
public class ThesisApplication implements CommandLineRunner {
	@Autowired
	ProfilEditor profilEditor;

	public static void main(String[] args) {
		SpringApplication.run(ThesisApplication.class, args);
	}

	@Override
	public void run(String ... args) throws Exception{
		profilEditor.create(182077829,"Janik Daub");
		profilEditor.create(180645494, "Ryota Kariya");
		profilEditor.create(181595941, "Ole Marschik");
	}
}