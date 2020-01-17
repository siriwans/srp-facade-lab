package srpfacadelab;

import java.util.List;

public class PlayerAction
{
    private RpgPlayer player;
    private InventoryManager inventory;

    public PlayerAction(RpgPlayer p, InventoryManager i)
    {
        player = p;
        inventory = i;
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = player.getGameEngine().getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item) {
        int weight = inventory.calculateInventoryWeight();
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && inventory.checkIfItemExistsInInventory(item))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                player.getGameEngine().playSpecialEffect("green_swirly");
            }

            return true;
        }
        
        if (item.isUnique() && item.isRare())
            player.getGameEngine().playSpecialEffect("blue_swirly");

        if (item.isRare())
            player.getGameEngine().playSpecialEffect("cool_swirly_particles");

        player.inventory.add(item);

        inventory.calculateStats();

        return true;
    }

    public void takeDamage(int damage) {
        if (damage < player.getArmour()) {
            player.getGameEngine().playSpecialEffect("parry");
        }

        if (player.getCarryingCapacity() / 2 > inventory.calculateInventoryWeight())
        {
            damage = (int)(damage * 0.75);
        }

        int damageToDeal = damage - player.getArmour();
        player.setHealth( player.getHealth() - damageToDeal);

        player.getGameEngine().playSpecialEffect("lots_of_gore");
    }
}