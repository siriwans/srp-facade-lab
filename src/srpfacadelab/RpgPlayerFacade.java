package srpfacadelab;


public class RpgPlayerFacade 
{
    private InventoryManager inventoryManager;
    private PlayerAction playerAction;

    public RpgPlayerFacade(InventoryManager inventoryManager, PlayerAction playerAction)
    {
        inventoryManager = this.inventoryManager;
        playerAction = this.playerAction;
    }

    public void useItem(Item item) {
        playerAction.useItem(item);
    }

    public boolean pickUpItem(Item item) {
        return playerAction.pickUpItem(item);
    }

    public void takeDamage(int damage) {
        playerAction.takeDamage(damage);
    }

    public void calculateStats() {
        inventoryManager.calculateStats();
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        return inventoryManager.checkIfItemExistsInInventory(item);
    }

    public int calculateInventoryWeight() {
        return inventoryManager.calculateInventoryWeight();
    }

}