/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.abdlquadri.util;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import ng.abdlquadri.TCPBridgedChatServer;

/**
 *
 * @author abdlquadri
 */
public class Deployer extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        JsonObject config = config();
        DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(config);

        vertx.deployVerticle(new TCPBridgedChatServer(), deploymentOptions);



    }

}