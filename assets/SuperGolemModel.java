// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class SuperGolemModel extends EntityModel<SuperGolem> {
	private final ModelPart golem;
	private final ModelPart nose;
	private final ModelPart feet;
	private final ModelPart footR;
	private final ModelPart footL;
	private final ModelPart arms;
	private final ModelPart armR;
	private final ModelPart armL;
	public SuperGolemModel(ModelPart root) {
		this.golem = root.getChild("golem");
		this.nose = this.golem.getChild("nose");
		this.feet = this.golem.getChild("feet");
		this.footR = this.feet.getChild("footR");
		this.footL = this.feet.getChild("footL");
		this.arms = this.golem.getChild("arms");
		this.armR = this.arms.getChild("armR");
		this.armL = this.arms.getChild("armL");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData golem = modelPartData.addChild("golem", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

		ModelPartData nose = golem.addChild("nose", ModelPartBuilder.create().uv(19, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, -3.0F));

		ModelPartData feet = golem.addChild("feet", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 3.0F, 0.0F));

		ModelPartData footR = feet.addChild("footR", ModelPartBuilder.create().uv(19, 23).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 0.0F));

		ModelPartData footL = feet.addChild("footL", ModelPartBuilder.create().uv(10, 16).mirrored().cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, -3.0F, 0.0F));

		ModelPartData arms = golem.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.5F, 0.0F));

		ModelPartData armR = arms.addChild("armR", ModelPartBuilder.create().uv(1, 24).cuboid(-2.0F, -0.5927F, -1.0053F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 0.0F));

		ModelPartData armL = arms.addChild("armL", ModelPartBuilder.create().uv(1, 16).mirrored().cuboid(0.0F, -0.5927F, -1.0053F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(SuperGolem entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		golem.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}