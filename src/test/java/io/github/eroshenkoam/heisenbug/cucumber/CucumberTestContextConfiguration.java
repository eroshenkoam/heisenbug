package io.github.eroshenkoam.heisenbug.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.github.eroshenkoam.heisenbug.HeisenbugApp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = HeisenbugApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
