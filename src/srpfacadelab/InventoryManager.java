package srpfacadelab;


public class InventoryManager
{
    private RpgPlayer player;

    public InventoryManager(RpgPlayer p)
    {
        player = p;
    }

    public void calculateStats() {
        for (Item i: player.inventory) {
            player.setArmour(player.getArmour() + i.getArmour());
        }
    }

    public boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: player.inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    public int calculateInventoryWeight() {
        int sum=0;
        for (Item i: player.inventory) {
            sum += i.getWeight();
        }
        return sum;
    }
}
        