package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {
    @Before
    public void setup() throws GemException {
            DriverManager.setUpBrowser();
    }
}
