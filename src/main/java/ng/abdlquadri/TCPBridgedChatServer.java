package ng.abdlquadri;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.eventbus.bridge.tcp.TcpEventBusBridge;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Created by abdlquadri on 12/22/15.
 */
public class TCPBridgedChatServer extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        vertx.eventBus().consumer("chat.to.server", (Message<JsonObject> message) -> {
            String timestamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(Date.from(Instant.now()));
            vertx.eventBus().publish("chat.to.client", new JsonObject().put("timestamp", timestamp).put("chat", message.body()) );
            logger.info(message.body());
        });

        TcpEventBusBridge bridge = TcpEventBusBridge.create(
                vertx,
                new BridgeOptions()
                        .addInboundPermitted(new PermittedOptions().setAddress("chat.to.server"))
                        .addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"))
        );


        bridge.listen(7000, res -> System.out.println("Ready"));
    }
}
