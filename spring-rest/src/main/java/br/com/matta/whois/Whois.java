package br.com.matta.whois;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Whois {

	@RequestMapping("/whoisfake")
    public String getAsNumber(@RequestParam(value="ip", defaultValue="201.69.33.209") String name) {
        return "AS | IP               | AS Name\n27699   | " + name + "    | Telefônica Brasil S.A,BR";
    }
}
