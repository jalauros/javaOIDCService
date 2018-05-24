package org.oidc.common;

import com.auth0.msg.Key;
import com.auth0.msg.KeyJar;
import java.util.List;
import org.oidc.service.data.State;

/**
 * Commonly used claims.
 * May need to be extended to accommodate additional claims
 */
public class AddedClaims implements Cloneable{

    /**
     * The client identifier, which is always required.
     */
    private String clientId;
    /**
     * The Issuer ID. This is the unique identifier of the OP/AS the
     * client is communicating with.
     */
    private String issuer;
    /**
     * Used to store keys
     */
    private KeyJar keyJar;
    /**
     * Whether response should be verified or not
     */
    private boolean shouldVerify;
    /**
     * https://tools.ietf.org/html/rfc6749#section-3.3
     *
     * The OAuth2 protocol is a delegated authorization mechanism, where an
     * application requests access to resources controlled by the user (the resource owner)
     * and hosted by an API (the resource server), and the authorization server issues the application
     * a more restricted set of credentials than those of the user.
     *
     * The scope parameter allows the application to express the desired scope of the access request.
     * In turn, the scope parameter can be used by the authorization server in the response to indicate
     * which scopes were actually granted (if they are different than the ones requested).
     */
    private List<String> scope;
    /**
     * Resource
     */
    private String resource;
    /**
     * Client authentication method - defined in enum ClientAuthenticationMethod
     * (The client authentication method to use if nothing else is specified.
     * Default is '' which means none.)
     */
    private ClientAuthenticationMethod clientAuthenticationMethod;
    /**
     * Serves as an in-memory cache
     */
    private State state;

    private String requestObjectSigningAlgorithm;

    private String algorithm;

    private String sigKid;

    private String requestMethod;

    private List<Key> keys;

    private boolean shouldAllowMissingKid;

    private String requestObjectEncryptionAlg;

    private String requestObjectEncryptionEnc;

    private String encryptionKid;

    private String target;

    public AddedClaims(String clientId, String issuer, KeyJar keyJar, boolean shouldVerify,
                       List<String> scope, String resource, ClientAuthenticationMethod clientAuthenticationMethod,
                       State state, String requestObjectSigningAlgorithm, String algorithm, String sigKid,
                       String requestMethod, List<Key> keys, boolean shouldAllowMissingKid,
                       String requestObjectEncryptionAlg, String requestObjectEncryptionEnc, String encryptionKid,
                       String target) {
        this.clientId = clientId;
        this.issuer = issuer;
        this.keyJar = keyJar;
        this.shouldVerify = shouldVerify;
        this.scope = scope;
        this.resource = resource;
        this.clientAuthenticationMethod = clientAuthenticationMethod;
        this.state = state;
        this.requestObjectSigningAlgorithm = requestObjectSigningAlgorithm;
        this.algorithm = algorithm;
        this.sigKid = sigKid;
        this.requestMethod = requestMethod;
        this.keys = keys;
        this.shouldAllowMissingKid = shouldAllowMissingKid;
        this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
        this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
        this.encryptionKid = encryptionKid;
        this.target = target;
    }

    /**
     * Used to clone an AddedClaims object
     * @return
     */
    public AddedClaims clone() {
        try {
            return (AddedClaims) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public String getClientId() {
        return clientId;
    }

    public String getIssuer() {
        return issuer;
    }

    public KeyJar getKeyJar() {
        return keyJar;
    }

    public boolean isShouldVerify() {
        return shouldVerify;
    }

    public List<String> getScope() {
        return scope;
    }

    public String getResource() { return resource; }

    public ClientAuthenticationMethod getClientAuthenticationMethod() {
        return clientAuthenticationMethod;
    }

    public State getState() {
        return state;
    }

    public String getRequestObjectSigningAlgorithm() {
        return requestObjectSigningAlgorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getSigKid() {
        return sigKid;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public boolean isShouldAllowMissingKid() {
        return shouldAllowMissingKid;
    }

    public String getRequestObjectEncryptionAlg() {
        return requestObjectEncryptionAlg;
    }

    public String getRequestObjectEncryptionEnc() {
        return requestObjectEncryptionEnc;
    }

    public String getEncryptionKid() {
        return encryptionKid;
    }

    public String getTarget() {
        return target;
    }

    public AddedClaimsBuilder buildAddedClaimsBuilder() {
        return new AddedClaimsBuilder(clientId, issuer, keyJar, shouldVerify, scope, resource, clientAuthenticationMethod, state,
                requestObjectSigningAlgorithm, algorithm, sigKid, requestMethod, keys, shouldAllowMissingKid, requestObjectEncryptionAlg,
                requestObjectEncryptionEnc, encryptionKid, target);
    }

    public static class AddedClaimsBuilder {
        private String clientId;
        private String issuer;
        private KeyJar keyJar;
        private boolean shouldVerify;
        private List<String> scope;
        private String resource;
        private ClientAuthenticationMethod clientAuthenticationMethod;
        private State state;
        private String requestObjectSigningAlgorithm;
        private String algorithm;
        private String sigKid;
        private String requestMethod;
        private List<Key> keys;
        private boolean shouldAllowMissingKid;
        private String requestObjectEncryptionAlg;
        private String requestObjectEncryptionEnc;
        private String encryptionKid;
        private String target;

        public AddedClaimsBuilder() {

        }

        public AddedClaimsBuilder(String clientId, String issuer, KeyJar keyJar, boolean shouldVerify,
                           List<String> scope, String resource, ClientAuthenticationMethod clientAuthenticationMethod,
                           State state, String requestObjectSigningAlgorithm, String algorithm, String sigKid,
                           String requestMethod, List<Key> keys, boolean shouldAllowMissingKid, String requestObjectEncryptionAlg,
                           String requestObjectEncryptionEnc, String encryptionKid, String target) {
            this.clientId = clientId;
            this.issuer = issuer;
            this.keyJar = keyJar;
            this.shouldVerify = shouldVerify;
            this.scope = scope;
            this.resource = resource;
            this.clientAuthenticationMethod = clientAuthenticationMethod;
            this.state = state;
            this.requestObjectSigningAlgorithm = requestObjectSigningAlgorithm;
            this.algorithm = algorithm;
            this.sigKid = sigKid;
            this.requestMethod = requestMethod;
            this.keys = keys;
            this.shouldAllowMissingKid = shouldAllowMissingKid;
            this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
            this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
            this.encryptionKid = encryptionKid;
            this.target = target;
        }

        public AddedClaimsBuilder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public AddedClaimsBuilder setIssuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public AddedClaimsBuilder setKeyJar(KeyJar keyJar) {
            this.keyJar = keyJar;
            return this;
        }

        public AddedClaimsBuilder setShouldVerify(boolean shouldVerify) {
            this.shouldVerify = shouldVerify;
            return this;
        }

        public AddedClaimsBuilder setScope(List<String> scope) {
            this.scope = scope;
            return this;
        }

        public AddedClaimsBuilder setResource(String resource) {
            this.resource = resource;
            return this;
        }

        public AddedClaimsBuilder setClientAuthenticationMethod(ClientAuthenticationMethod clientAuthenticationMethod) {
            this.clientAuthenticationMethod = clientAuthenticationMethod;
            return this;
        }

        public AddedClaimsBuilder setState(State state) {
            this.state = state;
            return this;
        }

        public AddedClaimsBuilder setRequestObjectSigningAlgorithm(String requestObjectSigningAlgorithm) {
            this.requestObjectSigningAlgorithm = requestObjectSigningAlgorithm;
            return this;
        }

        public AddedClaimsBuilder setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public AddedClaimsBuilder setSigKid(String sigKid) {
            this.sigKid = sigKid;
            return this;
        }

        public AddedClaimsBuilder setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public AddedClaimsBuilder setKeys(List<Key> keys) {
            this.keys = keys;
            return this;
        }

        public AddedClaimsBuilder setShouldAllowMissingKid(boolean shouldAllowMissingKid) {
            this.shouldAllowMissingKid = shouldAllowMissingKid;
            return this;
        }

        public AddedClaimsBuilder setRequestObjectEncryptionAlg(String requestObjectEncryptionAlg) {
            this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
            return this;
        }

        public AddedClaimsBuilder setRequestObjectEncryptionEnc(String requestObjectEncryptionEnc) {
            this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
            return this;
        }

        public AddedClaimsBuilder setEncryptionKid(String encryptionKid) {
            this.encryptionKid = encryptionKid;
            return this;
        }

        public AddedClaimsBuilder setTarget(String target) {
            this.target = target;
            return this;
        }

        public AddedClaims buildAddedClaims() {
            return new AddedClaims(clientId, issuer, keyJar, shouldVerify, scope, resource, clientAuthenticationMethod, state,
                    requestObjectSigningAlgorithm, algorithm, sigKid, requestMethod, keys, shouldAllowMissingKid, requestObjectEncryptionAlg,
                    requestObjectEncryptionEnc, encryptionKid, target);
        }
    }
}

