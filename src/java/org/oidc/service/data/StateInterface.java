package org.oidc.service.data;

import com.auth0.msg.InvalidClaimException;
import com.auth0.msg.Message;
import com.auth0.msg.SerializationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.security.KeyException;
import java.util.List;
import java.util.Map;
import org.oidc.common.MessageType;
import org.oidc.common.ValueException;

/**
 Contract for cache which can be implemented by developers using in-memory
 or DB.

 The State cache is a key,value data store. We name the keys
 stateKey. The values that are bound to the keys have an internal
 structure that again is key,value based. Here the keys are
 messagetypes and the values are the JSON representation of the
 corresponding Message.
 Beside messagetypes, there is one other key and that is ‘issuer’ which
 has as value the issuer ID of the Authorization Server.
 **/
public interface StateInterface{

    /**
     Gets State based off of stateKey
     * @param stateKey the key that identifies the State object
     * @return State State object connected to given key
     **/
    State getState(String stateKey) throws InvalidClaimException;

    /**
     Store a message
     * @param message request or response
     * @param stateKey the key under which the information is stored in cache
     * @param messageType type of message which will be used as sub-key
     **/
    void storeItem(Message message, String stateKey, MessageType messageType) throws InvalidClaimException, JsonProcessingException, SerializationException;

    /**
     * Retrieves data from cache (which can be of type json) and deserializes
     * to message according to message type
     * @param stateKey the key that identifies the State object
     * @param messageType determines message type
     * @return message returned from cache
     **/
    Message getItem(String stateKey, MessageType messageType) throws InvalidClaimException, ValueException;

    /**
     * Gets issuer ID based off of stateKey
     * @param stateKey the key that identifies the State object
     * @return issuer ID is the ID attached to a particular State
     * identified by the stateKey
     **/
    String getIssuer(String stateKey) throws InvalidClaimException, ValueException;

    /**
     Add a set of parameters and their value to a set of request args
     * @param args map of claims
     * @param messageType which request/response is wanted
     * @param stateKey the key that identifies the State object
     * @param parameters a list of parameters that will be looked
     * up in the args map as keys (if present) and modify if need be
     * @return an updated Map with keys from the list of params and
     * values being the values of those params in the message.
     * If the param does not appear in the item, it will not appear
     * in the returned dictionary.
     **/
    Map<String,Object> extendRequestArgs(Map<String,Object> args, MessageType messageType, String stateKey, List<String> parameters) throws ValueException, InvalidClaimException;

    /**
     Go through a set of items (by their type) and add the attribute-value
     pair that matches the list of parameters to the arguments.
     If the same parameter occurs in 2 different items, then the value in
     the later one will be the one used.

     * @param args initial set of arguments
     * @param stateKey the key that identifies the State object
     * @param parameters a list of parameters that we're looking for
     * @param messageTypes a list of message types specifying which messages we are interested in.
     * @return A possibly augmented map of arguments.
     **/
    Map<String,Object> multipleExtendRequestArgs(Map<String,Object> args, String stateKey, List<String> parameters, List<MessageType> messageTypes) throws InvalidClaimException, ValueException;

    /**
     Store the connection between a nonce value and a stateKey value.  This allows us later in the game
     to find the state if we have the nonce.
     * @param nonce an arbitrary string that can be used only once
     * @param stateKey the key that identifies the State object
     **/
    void storeStateKeyForNonce(String nonce, String stateKey);

    /**
     Find the stateKey value by providing the nonce value.  Will raise an exception if the nonce
     value is absent from the state DB
     * @param nonce an arbitrary string that can be used only once
     * @return state the state value
     **/
    String getStateKeyByNonce(String nonce) throws KeyException;

    /**
     Makes a new entry in the cache, stores the issuer with a new stateKey, and then returns the
     stateKey (random 32-character string)
     * @param issuer issuer that is bound to State
     * @return key connected to created State
     **/
    String createState(String issuer) throws JsonProcessingException, SerializationException;
}