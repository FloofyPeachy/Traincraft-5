package train.common.mtc;

import dan200.computercraft.api.peripheral.IComputerAccess;

public class MTCMessage {
    public String UUIDFrom;
    public String UUIDTo;
    public Object message;
    public Integer system;
    private double distanceTravelled = 0;

    public MTCMessage(String UUIDFrom, String UUIDTo, Object message, Integer system) {
        this.UUIDTo = UUIDTo;
        this.UUIDFrom = UUIDFrom;
        this.message = message;
        this.system = system;
        distanceTravelled = 0;
    }

    public Object[] getEventData(IComputerAccess computer) {
        return new Object[]{computer.getAttachmentName(), UUIDFrom, UUIDTo, message,
                system};
    }
}
