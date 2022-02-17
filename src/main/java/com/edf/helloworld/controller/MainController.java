/**
* Copyright (c) 2022 Apereo Foundation
* 
* Licensed under the Educational Community License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*             http://opensource.org/licenses/ecl2
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.edf.helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.sakaiproject.tool.api.SessionManager;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserNotDefinedException;

/**
 * MainController
 * 
 * This is the controller used by Spring MVC to handle requests
 * 
 */
@Slf4j
@Controller
public class MainController {

	private final String INDEX_TEMPLATE = "index";

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private UserDirectoryService userDirectoryService;

    @ModelAttribute("userName")
    public String userName() throws UserNotDefinedException {
        String userId = sessionManager.getCurrentSessionUserId();
        return userDirectoryService.getUser(userId).getDisplayName();
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        log.debug("Accessing the config editor index");
        return INDEX_TEMPLATE;
    }

}
