public class ShootingEnemyWeapon extends Weapon {
    private ShootingEnemy enemy;

    public ShootingEnemyWeapon(Game game, EntityManager entityManager, ShootingEnemy shooter) {
        super(game, entityManager, Weapon.ENEMY_SHOT_PAUSE);
        this.enemy = shooter;
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime > shotPause) {
            Bullet bullet = new Bullet(
                    game,
                    enemy.getX() + enemy.getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                    enemy.getY() + enemy.getHeight(),
                    0, -6,
                    Assets.enemyShot);
            bullet.setEntityManager(entityManager);
            bullet.setFriendly(false);
            bullet.setDamage(15);
            entityManager.addEntity(bullet);
            lastShotTime = System.nanoTime();
        }
    }
}
