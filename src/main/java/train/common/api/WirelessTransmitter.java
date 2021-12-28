package train.common.api;

import train.common.mtc.MTCMessage;

public interface WirelessTransmitter {

    void receiveMessage(MTCMessage message);
    void sendMessage(MTCMessage message);


}
