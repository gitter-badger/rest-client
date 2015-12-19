package org.jsoftware.restclient.plugins;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jsoftware.restclient.RestClientPlugin;
import org.jsoftware.restclient.RestClientResponse;

/**
 * Add <code>Authorization</code> header with value <code>Basic Authorization</code>
 * @author szalik
 */
public class AuthBasicPlugin implements RestClientPlugin {
    private final Header authHeader;

    public AuthBasicPlugin(String username, String password) {
        this.authHeader = createBasicAuthorizationHeader(username, password);
    }

    @Override
    public void plugin(PluginContext context, PluginChain chain) throws Exception {
        context.getRequest().addHeader(authHeader);
        chain.continueChain();
    }

    public static Header createBasicAuthorizationHeader(String username, String password) {
        byte[] encoding = Base64.encodeBase64((username + ":" + password).getBytes());
        String str = new String(encoding);
        return new BasicHeader("Authorization", "Basic " + str);
    }

}