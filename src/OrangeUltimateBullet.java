import java.awt.*;
import java.awt.image.BufferedImage;

public class OrangeUltimateBullet extends Bullet {
    private static final float SPEED = 20;
    public static final int DEFAULT_ULTIMATE_BULLET_WIDTH = 30, DEFAULT_ULTIMATE_BULLET_HEIGHT = 30;

    public OrangeUltimateBullet(Game game, float x, float y, BufferedImage bulletImg) {
        super(game, x, y, 0, SPEED, bulletImg);
        setFriendly(true);
        width = DEFAULT_ULTIMATE_BULLET_WIDTH;
        height = DEFAULT_ULTIMATE_BULLET_HEIGHT;
    }

    @Override
    public void tick() {
        y -= yMove;
        if (y < game.getHeight() / 2f) {
            Explosion explosion = new Explosion(game, entityManager,
                    500, 500,
                    this, Assets.bigExplosionImage, Explosion.EXPLOSION_DEFAULT_TTL);
            explosion.setX(game.getWidth() / 2f - 250);
            explosion.setY(game.getHeight() / 2f - 250);
            explosion.setScale(6);
            entityManager.addEntity(explosion);
            Assets.playSound(Assets.orangeUltimateExplosion);
            for (int i = 0; i < entityManager.getEntities().size(); i++) {
                Entity e = entityManager.getEntities().get(i);
                if (!e.isFriendly()) {
                    if (!(e instanceof Boss)) {
                        i--;
                        entityManager.removeEntity(e);
                    } else {
                        e.getHit(this, entityManager);
                    }
                    entityManager.getPlayer().heal(5);
                }
            }
            entityManager.removeEntity(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImg, Math.round(x), Math.round(y),
                DEFAULT_ULTIMATE_BULLET_WIDTH, DEFAULT_ULTIMATE_BULLET_HEIGHT, null);
    }
}
