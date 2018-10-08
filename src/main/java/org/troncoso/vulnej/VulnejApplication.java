package org.troncoso.vulnej;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class VulnejApplication {

    @GetMapping
    public ResponseEntity<String> get(final @RequestParam(value = "cmd", required = false) String cmd)
            throws IOException {

        if (StringUtils.hasText(cmd)) {
            final ProcessBuilder builder = new ProcessBuilder();
            builder.command("sh", "-c", cmd);

            final Scanner s = new Scanner(builder.start().getInputStream()).useDelimiter("\\A");
            final String result = s.hasNext() ? s.next() : "";

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hello my friend!", HttpStatus.OK);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(VulnejApplication.class, args);
    }
}
