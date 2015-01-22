/*
 * Copyright 2013 eXo Platform SAS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@juzu.Application(defaultController = org.juzu.removemyaccount.portlet.frontend.JuZFrontendApplication.class)
@Portlet(name="FrontendPortlet")
@Bindings({
        @Binding(value = RemoveMyAccountService.class, implementation = RemoveMyAccountJCRImpl.class),
        @Binding(value = SessionProviderService.class), @Binding(value = NodeHierarchyCreator.class),
        @Binding(value = OrganizationService.class)
})
@WebJars(@WebJar("jquery"))
@Scripts({
        @Script(id = "jquery", value = "jquery/1.10.2/jquery.js"),
        @Script(value = "javascripts/removemyaccount.js", depends = "jquery")
})
@Stylesheets(@Stylesheet("css/removemyaccount.css"))
@Assets("*")
package org.juzu.removemyaccount.portlet.frontend;

import juzu.plugin.asset.*;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
import juzu.plugin.webjars.WebJar;
import juzu.plugin.webjars.WebJars;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.hierarchy.NodeHierarchyCreator;
import org.exoplatform.services.organization.OrganizationService;
import org.juzu.removemyaccount.commons.services.RemoveMyAccountJCRImpl;
import org.juzu.removemyaccount.commons.services.RemoveMyAccountService;
