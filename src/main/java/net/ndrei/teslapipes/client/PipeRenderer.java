package net.ndrei.teslapipes.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.ndrei.teslapipes.entities.PipeEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by CF on 2017-03-12.
 */
public class PipeRenderer extends TileEntitySpecialRenderer<PipeEntity> {
    @Override
    public void renderTileEntityAt(PipeEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
//        if (ClientProxy.MIDDLE_CABLE_SPRITE == null) {
//            return; // no texture to render with
//        }
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.0F, (float) y + 0.0F, (float) z + 0.0F);
        float mn = 1.0f / 32.0f;
        GlStateManager.scale(mn, mn, mn);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

//        TextureAtlasSprite tas = ClientProxy.MIDDLE_CABLE_SPRITE;

//        buffer.pos(0, 14, 14).tex(tas.getInterpolatedU(0), tas.getInterpolatedV(7)).endVertex();
//        buffer.pos(11, 14, 14).tex(tas.getInterpolatedU(5.5), tas.getInterpolatedV(7)).endVertex();
//        buffer.pos(11, 14, 18).tex(tas.getInterpolatedU(5.5), tas.getInterpolatedV(9)).endVertex();
//        buffer.pos(0, 14, 18).tex(tas.getInterpolatedU(0), tas.getInterpolatedV(9)).endVertex();
//
//        buffer.pos(0, 14, 18).tex(tas.getInterpolatedU(0), tas.getInterpolatedV(7)).endVertex();
//        buffer.pos(11, 14, 18).tex(tas.getInterpolatedU(5.5), tas.getInterpolatedV(7)).endVertex();
//        buffer.pos(11, 18, 18).tex(tas.getInterpolatedU(5.5), tas.getInterpolatedV(9)).endVertex();
//        buffer.pos(0, 18, 18).tex(tas.getInterpolatedU(0), tas.getInterpolatedV(9)).endVertex();

        float percent = 0.75f;

        float center = 4.0f * percent;
        this.renderCube(buffer,
                16.0f - center, 16.0f - center, 16.0f - center,
                16.0f + center, 16.0f + center, 16.0f + center);

        float side = 1.0f * percent;
        this.renderCube(buffer,
                0.01f, 16.0f - side, 16.0f - side,
                16.0f, 16.0f + side, 16.0f + side);
        this.renderCube(buffer,
                16.00f, 16.0f - side, 16.0f - side,
                31.99f, 16.0f + side, 16.0f + side);
        this.renderCube(buffer,
                16.0f - side, 16.0f - side, 0.01f,
                16.0f + side, 16.0f + side, 16.0f);
        this.renderCube(buffer,
                16.0f - side, 16.0f - side, 16.00f,
                16.0f + side, 16.0f + side, 31.99f);
        this.renderCube(buffer,
                16.0f - side, 0.01f, 16.0f - side,
                16.0f + side, 16.0f, 16.0f + side);
        this.renderCube(buffer,
                16.0f - side, 16.00f, 16.0f - side,
                16.0f + side, 31.99f, 16.0f + side);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableLighting();
        GlStateManager.disableTexture2D();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    private void renderCube(VertexBuffer quads, float x1, float y1, float z1, float x2, float y2, float z2)
    {
        quads.pos(x1, y1, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y2, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y1, z1).color(27, 192, 179, 255).endVertex();

        quads.pos(x1, y1, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y1, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y2, z2).color(27, 192, 179, 255).endVertex();

        quads.pos(x1, y1, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y1, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y2, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y2, z1).color(27, 192, 179, 255).endVertex();

        quads.pos(x2, y1, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y1, z2).color(27, 192, 179, 255).endVertex();

        quads.pos(x1, y1, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y1, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y1, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y1, z2).color(27, 192, 179, 255).endVertex();

        quads.pos(x1, y2, z1).color(27, 192, 179, 255).endVertex();
        quads.pos(x1, y2, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z2).color(27, 192, 179, 255).endVertex();
        quads.pos(x2, y2, z1).color(27, 192, 179, 255).endVertex();
    }
}
