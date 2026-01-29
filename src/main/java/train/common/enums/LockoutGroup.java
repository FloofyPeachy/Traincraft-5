package train.common.enums;

import train.common.utils.lockout.ILockoutGroup;


public enum LockoutGroup implements ILockoutGroup
{
    DEFAULT,
    ADMIN,

    //tbea
    SPR("932ac07f-59c3-43ec-b11d-a26ea08c77d9"), //steampunk rail
    SCP("932ac07f-59c3-43ec-b11d-a26ea08c77d9"), //scp

    //begin the bida spam of reporting marks
    FNCC("d46213e9-ea09-40e7-9ec9-595903d98e17"), //fox, north coast & cascades
    FMSR("d46213e9-ea09-40e7-9ec9-595903d98e17"), //fox, marble & seaboard ry
    DES("d46213e9-ea09-40e7-9ec9-595903d98e17"), //detroit export system
    ANE("d46213e9-ea09-40e7-9ec9-595903d98e17"), //avanste northeastern
    CPV("d46213e9-ea09-40e7-9ec9-595903d98e17"), //carbondale & pine valley
    CNRC("d46213e9-ea09-40e7-9ec9-595903d98e17"), //cedar northern
    SNCT("d46213e9-ea09-40e7-9ec9-595903d98e17"), //seattle & north coast ry (protolance textures only)
    BIDA("d46213e9-ea09-40e7-9ec9-595903d98e17"), //misc mark for various bida railroads i dont think are important enough to have their own groups
    LRR("d46213e9-ea09-40e7-9ec9-595903d98e17"), //latrans range
    PDEV("d46213e9-ea09-40e7-9ec9-595903d98e17"), //Peninsula Development & Improvement Company Railroad
    SGPR(),//sargosa
    ADT(),//divs ADT

    //Cubed
    MAG("47959692-7c74-4734-8a43-7fc59bc9c8e8"), //Magnolia Acres MFG
    CUBED("47959692-7c74-4734-8a43-7fc59bc9c8e8"), //cubed railroads

    //messi
    MTS("8eb7bc43-c2c4-4b45-8329-4c5260c0fbf0"), //Morristown Tenneva & Southern
    LW ("8eb7bc43-c2c4-4b45-8329-4c5260c0fbf0"), //Lisha & Watson
    // wcfb75
    GCM("56af8eef-e84f-4eab-ad56-ba59f8431176"), //Galesburg Clinton & Midland

    //mano
    CWL("ae85a873-2ce8-4a28-9589-fddae7e0af65"), //clear water lakes

    //maxywolfi
    ANW("fc29999b-606d-46a2-a2cc-80042df47544"), //akworth northwestern

    //riggs64
    DRXL("b57bdcf8-561b-426e-9603-5a8ad35f9375"), //drexel co

    //bigfishie
    DLMR("8ce3fa8e-9f27-48c5-b97e-de12ad735296"),//deadwood & la mesa
    AGW("8ce3fa8e-9f27-48c5-b97e-de12ad735296"),// adelante great western or whatsitsface spelled lol

    JCTransit(),

    //Cheesewheel
    CDCS("43efda8c-f591-4f7e-bc54-c4978484e69f"), //Clarksville, Deer Creek & Southern
    ;

    /**
     * When a value is here it is impossible to change the group owner
     * without changing it here. This also has the affect of no matter
     * where you run this you will always be the owner.
     */
    final String GroupDefaultUUIDOwner;
    
    LockoutGroup()
    {
        GroupDefaultUUIDOwner = "SYSTEM";
    }
    
    LockoutGroup(String groupDefaultUUIDOwner)
    {
        GroupDefaultUUIDOwner = groupDefaultUUIDOwner;
    }

    @Override
    public String groupUUIDOwner()
    {
        return GroupDefaultUUIDOwner;
    }
}



