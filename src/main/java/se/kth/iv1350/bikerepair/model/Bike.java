package se.kth.iv1350.bikerepair.model;

public class Bike {
    private final String brand;
    private final String model;
    private final String serialNumber;


    /**
     * Creates a new instance.
     * @param brand What brand the bike has
     * @param model What model the bike has
     * @param serialNumber What serial number the bike has
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public BikeDTO createDTO() {
        return new BikeDTO(brand, model, serialNumber);
    }
}
