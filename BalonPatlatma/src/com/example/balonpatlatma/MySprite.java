package com.example.balonpatlatma;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;



public class MySprite extends Sprite{

	public boolean aktif;
	public int durum ;
	public String R;
	public int sira;
	public MySprite(float pX, float pY,TextureRegion pTextureRegion,int durum,boolean aktif,String R,int sira) {
		
		super(pX, pY, pTextureRegion);
		this.durum = durum;
		this.aktif = aktif;
		this.R = R;
		this.sira = sira;
		
	}
	
	

}
