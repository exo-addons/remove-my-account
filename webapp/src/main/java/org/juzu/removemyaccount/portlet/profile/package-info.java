/**
 * Created by exoplatform on 15/01/15.
 */
@Application(defaultController = JuzProfileApplication.class)
@Portlet(name = "ProfilePorlet")
@WebJars(@WebJar("jquery"))
@Scripts({
        @Script(id = "jquery", value = "jquery/1.10.2/jquery.js"),
        @Script(value = "javascripts/remove-account-profile.js", depends = "jquery")
})
@Assets("*")
package org.juzu.removemyaccount.portlet.profile;

import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Scripts;
import juzu.plugin.portlet.Portlet;
import juzu.plugin.webjars.WebJar;
import juzu.plugin.webjars.WebJars;