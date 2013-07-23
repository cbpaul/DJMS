package com.paul.webapp.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.subethamail.wiser.Wiser;

import com.opensymphony.xwork2.Action;
import com.paul.model.User;
import com.paul.service.UserManager;

public class PasswordHintActionTest extends BaseActionTestCase {
     @Autowired
    private PasswordHintAction action;
     @Autowired
    private UserManager userManager;

    @Test
    public void testExecute() throws Exception {
        // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(getSmtpPort());
        wiser.start();
        
        action.setUsername("user");
        assertEquals("success", action.execute());
        assertFalse(action.hasActionErrors());

        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the request
        assertNotNull(action.getSession().getAttribute("messages"));
    }

    @Test
    public void testExecuteNoUserName() throws Exception {
        action.setUsername(null);
        assertEquals(Action.INPUT, action.execute());
        assertTrue(action.hasActionErrors());
    }

    @Test
    public void testExecuteWrongUserName() throws Exception {
        action.setUsername("UNKNOWN123");
        assertEquals(Action.INPUT, action.execute());
        assertTrue(action.hasActionErrors());
    }

    @Test
    public void testExecuteNoPasswordHintUserName() throws Exception {
        action.setUsername("manager");
        final User user = userManager.getUserByUsername("admin");
        user.setPasswordHint("  ");
        userManager.save(user);
        assertEquals(Action.INPUT, action.execute());
        assertTrue(action.hasActionErrors());
        user.setPasswordHint(null);
        userManager.save(user);
        assertEquals(Action.INPUT, action.execute());
        assertTrue(action.hasActionErrors());
    }
}
