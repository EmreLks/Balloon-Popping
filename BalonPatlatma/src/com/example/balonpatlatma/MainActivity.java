package com.example.balonpatlatma;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
public class MainActivity extends BaseGameActivity 
{
	private Sound patlat,patlatSiyah;
	private Music muzik;
	private int ps1=0,ps2=0,ps3=0,ps4=0;
	//////////////////////////////////////////
	BuildableTexture bTex;
    Font font;
    ChangeableText cTexSkor,cTexGoster,CTexBolum1,CTexBolum2,CTexBolum3,cTexToplam;
    ChangeableText cTexKirmizi,cTexSari,cTexYesil,cTexSiyah;
	///////////////////////////////////////////
	private static final int C_GENISLİK = 512;
    private static final int C_YUKSEKLİK = 1024;
    private EngineOptions engineOptions;
    private Camera camera;
    private Engine engine;
    private PhysicsWorld physicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_DEATH_STAR_I), false);
    private PhysicsWorld physicsWorld2 = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_DEATH_STAR_I), false);
    private PhysicsWorld physicsWorld3 = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_DEATH_STAR_I), false);
    private FixtureDef fixDef = PhysicsFactory.createFixtureDef(0.5f, 0.5f, 0.000001f);
	Scene sceneMenu,sceneOyun,sceneSkor,sceneHakkinda;
	//////////////////////////////////////////////////////
	private Texture texMute,texNmute;
	private Texture texMenu,texPlay,texHakkinda,texCikis,texDev; 
	private Texture texSonuc;
	private Texture texArka,texArka2,texArka3,texBalonK,texBalonY,texBalonSari,texBalonSiyah;
	//////////////////////////////////////////////////////
	private TextureRegion texRegMute,texRegNMute,texRegMenu,texRegPlay,texRegHakkinda,texRegCikis,texRegDev;
	private TextureRegion texRegArka,texRegArka2,texRegArka3,texRegBalonK,texRegBalonY,texRegBalonSari,texRegBalonSiyah;
	private TextureRegion texRegSonuc;
	/////////////////////////////////////////////////////////
	private Sprite spriteArka;
	private Sprite spriteMenu,spritePlay,spriteCikis,spriteHakkinda,spriteDev;
	private Sprite spriteSonuc,spriteMute,spriteNmute;
	///////////////////////////////////////////////////////
	List<MySprite> spriteBalonK = new ArrayList<MySprite>();
	List<MySprite> spriteBalonSari = new ArrayList<MySprite>();
	List<MySprite> spriteBalonSiyah = new ArrayList<MySprite>();
	List<MySprite> spriteBalonY = new ArrayList<MySprite>();
	private MySprite gecici1,gecici2,gecici3;
	///////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	List<Body> bodyBk = new ArrayList<Body>();
	List<Body> bodyBy = new ArrayList<Body>();
	List<Body> bodyBSari = new ArrayList<Body>();
	List<Body> bodyBSiyah = new ArrayList<Body>();
	//////////////////////////////////////////////////////
	
	private int i=0;
	//////////////////////////////////////////////////////////////////////
	private TimerHandler timerOyunSuresi,timergorunmezlik,timerrenkDegis;
	private int skorBolum1=0,skorBolum2=0,skorBolum3=0;
	int elemanSayisi=6;
	/////////////////////////////////////////////////////////
	
	@Override
	public Engine onLoadEngine() {
		camera = new Camera(0,0,C_GENISLİK,C_YUKSEKLİK);
	    engineOptions = new EngineOptions(true,ScreenOrientation.PORTRAIT,new FillResolutionPolicy(),camera);

		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		engineOptions.setNeedsMusic(true);
		engineOptions.setNeedsSound(true);

		engine = new Engine(engineOptions);

		return engine;
	}

	@Override
	public void onLoadResources() {
		
		
		texMenu = new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texPlay = new Texture(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texHakkinda = new Texture(128,32,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texCikis = new Texture(128,64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texDev = new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texMute = new Texture(64,64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texNmute = new Texture(64,64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		////////////////////////////////////////////////////////////////////////
		texArka = new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texArka2 = new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texArka3= new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texBalonK = new Texture(64,64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texBalonY= new Texture(64,64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texBalonSari = new Texture(64,64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		texBalonSiyah= new Texture(64,64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		/////////////////////////////////////////////////////////////////////////////////
		texSonuc = new Texture(512,1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//////////////////////////////////////////////////////////////////////////////////
		texRegMute = TextureRegionFactory.createFromAsset(texMute, this, "gfx/mute.png",0,0);
		texRegNMute = TextureRegionFactory.createFromAsset(texNmute, this, "gfx/nmute.png",0,0);
		texRegDev = TextureRegionFactory.createFromAsset(texDev, this, "gfx/texdev.png",0,0);
		texRegMenu = TextureRegionFactory.createFromAsset(texMenu, this, "gfx/menu.png",0,0);
		texRegPlay = TextureRegionFactory.createFromAsset(texPlay, this, "gfx/oyna.png",0,0);
		texRegHakkinda = TextureRegionFactory.createFromAsset(texHakkinda, this, "gfx/hakkinda1.png",0,0);
		texRegCikis = TextureRegionFactory.createFromAsset(texCikis, this, "gfx/cikis.png",0,0);
		//////////////////////////////////////////////////////////////////////////////////////////
		texRegSonuc = TextureRegionFactory.createFromAsset(texSonuc, this, "gfx/sonuc.png",0,0);
		//////////////////////////////////////////////////////////////////////////////////////////
		texRegArka = TextureRegionFactory.createFromAsset(texArka, this, "gfx/arkaplan.jpg",0,0);
		texRegArka2 = TextureRegionFactory.createFromAsset(texArka2, this, "gfx/arkaplan1.png",0,0);
		texRegArka3= TextureRegionFactory.createFromAsset(texArka3, this, "gfx/arkaplan3.jpg",0,0);
		texRegBalonK = TextureRegionFactory.createFromAsset(texBalonK, this, "gfx/red.png",0,0);
		texRegBalonY = TextureRegionFactory.createFromAsset(texBalonY, this, "gfx/green.png",0,0);
		texRegBalonSari = TextureRegionFactory.createFromAsset(texBalonSari, this, "gfx/yellow.png",0,0);
		texRegBalonSiyah = TextureRegionFactory.createFromAsset(texBalonSiyah, this, "gfx/green.png",0,0);
		
		
		/////////////////////////////////////////////
		if(bTex == null)
		{
			bTex = new BuildableTexture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		}
		else
		{
			bTex = null;
			bTex = new BuildableTexture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		}
		////////////////////////////////////////////////////////////////////
		try{
			if(patlat == null)
			{
				patlat = SoundFactory.createSoundFromAsset(this.engine.getSoundManager(),this, "gfx/pop.mp3");
			}
			else
			{
				patlat = null;
				patlat = SoundFactory.createSoundFromAsset(this.engine.getSoundManager(),this, "gfx/pop.mp3");
			}
		}
		catch(IllegalStateException e)
		{
			Log.d("Illegal Bir Durum","illegal bir durum oldu");
		}
		catch(IOException e)
		{
			Log.d("Ioexception", "ıoexception oldiiii");
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		try{
			if(patlatSiyah == null)
			{
				patlatSiyah = SoundFactory.createSoundFromAsset(this.engine.getSoundManager(),this, "gfx/popsiyah.mp3");
			}
			else
			{
				patlatSiyah = null;
				patlatSiyah = SoundFactory.createSoundFromAsset(this.engine.getSoundManager(),this, "gfx/popsiyah.mp3");
			}
		}
		catch(IllegalStateException e)
		{
			Log.d("Illegal Bir Durum","illegal bir durum oldu");
		}
		catch(IOException e)
		{
			Log.d("Ioexception", "ıoexception oldiiii");
		}
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		try
		{
			if(muzik == null)
			{
				muzik = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(),this, "gfx/pou.mp3");
			}
			else
			{
				muzik = null;
				muzik = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(),this, "gfx/pou.mp3");
			}
		}
		catch(IllegalStateException e)
		{
			Log.d("Illegal Bir Durum","illegal bir durum oldu");
		}
		catch(IOException e)
		{
			Log.d("Ioexception", "ıoexception oldiiii");
		}
		
		///////////////////////////////////////////
		Texture [] textures = {bTex,texMute,texNmute,texArka,texArka2,texArka3,texSonuc,texPlay,texHakkinda,texDev,texCikis,texMenu,texBalonK,texBalonY,texBalonSari,texBalonSiyah};

		mEngine.getTextureManager().loadTextures(textures);
		
		this.font = FontFactory.createStrokeFromAsset(this.bTex, this, "gfx/X-Files.ttf", 40, true, Color.RED, 2, Color.rgb(97, 134, 147));
		mEngine.getFontManager().loadFont(font);
		
		cTexGoster = new ChangeableText(40, 100, this.font, "",50);
		cTexSkor = new ChangeableText(40, 200, this.font, "",50);
		CTexBolum1 = new ChangeableText(40, 300, this.font, "",50);
		CTexBolum2 = new ChangeableText(40, 400, this.font, "",50);
		CTexBolum3 = new ChangeableText(40, 500, this.font, "",50);
		cTexToplam = new ChangeableText(40, 600, this.font, "",50);
		cTexKirmizi = new ChangeableText(300, 100, this.font,"" , 50);
		cTexSari = new ChangeableText(350, 100, this.font,"" , 50);
		cTexYesil = new ChangeableText(400, 100, this.font,"" , 50);
		cTexSiyah = new ChangeableText(450, 100, this.font,"" , 50);
		
		
		/*patlat.setVolume(80.0f);
		patlatSiyah.setVolume(50.0f);
		muzik.setVolume(30.0f);*/
		
		
	}
	
	@Override
	public Scene onLoadScene() {
		
		this.engine.registerUpdateHandler(new FPSLogger());
		this.sceneMenu = new Scene();
		this.sceneHakkinda = new Scene();
		this.sceneOyun = new Scene();
		
		MenuOlustur();
		Hakkinda();
		BolumOlustur1();
		
		return this.sceneMenu;
		
	}
	private void Hakkinda()
	{
		spriteDev = new Sprite(0, 0, this.texRegDev);
		this.sceneHakkinda.attachChild(spriteDev);
	}
	
	private void MenuOlustur()
	{
		if(!muzik.isPlaying())
		{
			muzik.play();
		}
		spriteMenu = new Sprite(0, 0, this.texRegMenu);
		spritePlay = new Sprite(0,500,this.texRegPlay)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
    			if(pSceneTouchEvent.isActionUp())
    			{   
    				engine.setScene(sceneOyun);
    			}
                return true;
			}
		};
		spriteHakkinda = new Sprite(50,600,this.texRegHakkinda)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.isActionUp())
    			{   
    				engine.setScene(sceneHakkinda);
    			}
                return true;
			}
		};
		spriteCikis = new Sprite(350,900,this.texRegCikis)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if(pSceneTouchEvent.isActionUp())
    			{   
    				finish();
    	            System.exit(0);
    			}
                return true;
			}
		};
		/////////////////////////////////////////////////////////
		spriteNmute = new Sprite(100,900,this.texRegNMute)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if(pSceneTouchEvent.isActionUp())
    			{   
					engine.getMusicManager().setMasterVolume(1.0f);
					engine.getSoundManager().setMasterVolume(1.0f);
    			}
                return true;
			}
		};
		//////////////////////////////////////////////////////////
		spriteMute = new Sprite(200,900,this.texRegMute)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if(pSceneTouchEvent.isActionUp())
    			{   
					engine.getMusicManager().setMasterVolume(0);
					engine.getSoundManager().setMasterVolume(0);
    			}
                return true;
			}
		};
		
		/////////////////////////////////////////////////////////
		
		this.sceneMenu.attachChild(spriteMenu);
		this.sceneMenu.attachChild(spriteHakkinda);
		this.sceneMenu.attachChild(spritePlay);
		this.sceneMenu.attachChild(spriteCikis);
		this.sceneMenu.attachChild(spriteMute);
		this.sceneMenu.attachChild(spriteNmute);
		this.sceneMenu.registerTouchArea(spritePlay);
		this.sceneMenu.registerTouchArea(spriteHakkinda);
		this.sceneMenu.registerTouchArea(spriteMute);
		this.sceneMenu.registerTouchArea(spriteNmute);
		this.sceneMenu.registerTouchArea(spriteCikis);
	}
	private void BolumOlustur1()
	{
		ps1=0 ; ps2=0 ; ps3=0 ; ps4=0;
		Line dy1,dy2,dd1,dd2;
		Body bodyDy1,bodyDy2,bodyDd1,bodyDd2;
		spriteArka = new  Sprite(0, 0, this.texRegArka);
		/////////////////////////////////////////
		dy1 = new Line(30,300,30+450,300,40);
		dy1.setColor(1.0f, 0.0f, 0.0f);
		
		
		dd1 = new Line(30+450,300,30+450,300+600,40);
		dd1.setColor(1.0f, 0.0f, 0.0f);
		
		
		dy2 = new Line(30+450,300+600,30,300+600,40);
		dy2.setColor(1.0f, 0.0f, 0.0f);
		
		
		dd2 = new Line(30, 300+600, 30,300,40);
		dd2.setColor(1.0f, 0.0f, 0.0f);
		

		////////////////////////////////////////////////////////
		this.sceneOyun.attachChild(spriteArka);
		
		///////////////////////////////////////////////////
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,300,this.texRegBalonK,0,true,"K",i);
			spriteBalonK.add(item);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,400,this.texRegBalonY,0,true,"Y",i);
			item.setColor(0, 1.0f, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonY.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,500,this.texRegBalonSari,0,true,"Sari",i);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSari.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,600,this.texRegBalonSiyah,0,true,"Siyah",i);
			item.setColor(0, 0, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSiyah.add(item);
		}
		///////////////////////////////////////////////////
		this.sceneOyun.setOnSceneTouchListener(new IOnSceneTouchListener() 
		{
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
			{
				// TODO Auto-generated method stub
				for(MySprite item:spriteBalonK)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici1 = item;
					}
				}
				for(MySprite item:spriteBalonY)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici1 = item;
					}
				}
				for(MySprite item:spriteBalonSari)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici1 = item;
					}
				}
				for(MySprite item:spriteBalonSiyah)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici1 = item;
					}
				}
				if(pSceneTouchEvent.isActionUp())
				{
					if(gecici1!=null)
					{
						
						 gecici1.aktif=false;
				 		 gecici1.detachSelf();
				 		 if(gecici1.R.equals("K"))
				 		 {
				 			 spriteBalonK.remove(gecici1);
				 			 skorBolum1 = skorBolum1 + 10;
		                     ps1++;
		                     ///
		                     bodyBk.get(gecici1.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBk.get(gecici1.sira));
							 //bodyBk.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici1 = null;
				 		 }
				 		 else if(gecici1.R.equals("Y"))
				 		 {
				 			spriteBalonY.remove(gecici1);
				 			skorBolum1 = skorBolum1 + 5;
				 			ps2++;
				 			///
		                     bodyBy.get(gecici1.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBy.get(gecici1.sira));
							 //bodyBy.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici1 = null;
				 		 }
				 		 else if(gecici1.R.equals("Sari"))
				 		 {
				 			spriteBalonSari.remove(gecici1);
				 			skorBolum1 = skorBolum1 + 20;
				 			ps3++;
				 			///
		                     bodyBSari.get(gecici1.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSari.get(gecici1.sira));
							 //bodyBSari.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici1 = null;
				 		 }
				 		 else if(gecici1.R.equals("Siyah"))
				 		 {
				 			spriteBalonSiyah.remove(gecici1);
				 			skorBolum1 = skorBolum1 - 10;
				 			ps4++;
				 			///
		                     bodyBSiyah.get(gecici1.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSiyah.get(gecici1.sira));
							 //bodyBSiyah.remove(gecici.sira);
		                     ///
							 if(patlatSiyah != null)
					 		 {
								 patlatSiyah.play();
					 		 }
							 gecici1 = null;
				 		 }
					}
					
			 		 
				}
				return false;
			}
		});
		
		
		/////////////////////////////////////////			
		/////////////////////////////////////////
		////////////////////////////////////////
		
		for(int i=0;i<spriteBalonK.size();i++)
		{
			if(spriteBalonK.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld,spriteBalonK.get(i).getX() + 64,spriteBalonK.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(spriteBalonK.get(i), item, true, false));
				item.setLinearVelocity(80,10);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBk.add(item);
			}
			
	
		}
		for(int i=0;i<spriteBalonY.size();i++)
		{
			if(spriteBalonY.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld,spriteBalonY.get(i).getX() + 64,spriteBalonY.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(spriteBalonY.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBy.add(item);
			}
			
		}
		for(int i=0;i<spriteBalonSari.size();i++)
		{
			if(spriteBalonSari.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld,spriteBalonSari.get(i).getX() + 64,spriteBalonSari.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(spriteBalonSari.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBSari.add(item);
			}
			
		}
		for(int i=0;i<spriteBalonSiyah.size();i++)
		{
			if(spriteBalonSiyah.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld,spriteBalonSiyah.get(i).getX() + 64,spriteBalonSiyah.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(spriteBalonSiyah.get(i), item, true, false));
				item.setLinearVelocity(-90,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBSiyah.add(item);
			}
			
		}
		
		//////////////////////////////////////////
		bodyDy1 = PhysicsFactory.createLineBody(this.physicsWorld, dy1, fixDef);
		bodyDy2 = PhysicsFactory.createLineBody(this.physicsWorld, dy2, fixDef);
		bodyDd1 = PhysicsFactory.createLineBody(this.physicsWorld, dd1, fixDef);
		bodyDd2 = PhysicsFactory.createLineBody(this.physicsWorld, dd2, fixDef);
		/////////////////////////////////////////////
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(dy1, bodyDy1, false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(dd1, bodyDd1, false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(dd2, bodyDd2, false, false));
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(dy2, bodyDy2, false, false));
		//////////////////////////////////////////
		
		
		cTexGoster.setColor(1f,0,0);
		
		//////////////////////////////////////////////
		 this.engine.registerUpdateHandler(timergorunmezlik = new TimerHandler(1.0f,true,new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				
				
				for(final Sprite item:spriteBalonSari)
				{
					if(item.isVisible())
					{
						item.setVisible(false);
					}
					else
					{
						item.setVisible(true);
					}
				}
				
				
			}
		}));
		 
		 
		 Random r = new Random();
		 float deger = r.nextFloat() % 10 +1;
		 this.engine.registerUpdateHandler(timerrenkDegis = new TimerHandler(deger,true,new ITimerCallback() {
				
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					
					
					for(final MySprite item:spriteBalonSiyah)
					{
						if(item.durum==0)
						{
							item.setColor(0, 1.0f, 0);
							item.durum = 1;
						}
						else if(item.durum == 1)
						{
							item.setColor(0.0f,0.0f, 0.0f);
							item.durum = 0;
						}
					}
					
					for(final MySprite item:spriteBalonY)
					{
						if(item.durum==0)
						{
							item.setColor(0, 0, 0);
							item.durum = 1;
						}
						else if(item.durum == 1)
						{
							item.setColor(0.0f,1.0f, 0.0f);
							item.durum = 0;
						}
					}
					
					
				}
			}));
		 ////////////////////////////////////////////
		
		 
		//////////////////////////////////////////////
		
		this.sceneOyun.registerUpdateHandler(new IUpdateHandler() {
			
			float artisx=0,artisy=0,artisx1=0,artisy1=0;
			@Override
			public void onUpdate(float pSecondsElapsed) 
			{
				if(!muzik.isPlaying())
				{
					muzik.play();
				}
				cTexGoster.setText("Puan: " + skorBolum1);
				
				cTexKirmizi.setColor(1.0f, 0, 0);
				cTexYesil.setColor(0, 1.0f, 0);
				cTexSari.setColor(255.0f/255.0f, 255.0f/216.0f, 0);
				cTexSiyah.setColor(0, 0, 0);
				
				cTexKirmizi.setText(""+ps1);
				cTexYesil.setText(""+ps2);
				cTexSari.setText(""+ps3);
				cTexSiyah.setText(""+ps4);
				
				
				for(Body item:bodyBSiyah)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}							
					}
					
				}
				for(Body item:bodyBy)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}
						
					}
				}
				
				for(Body item:bodyBk)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx,artisy);
					}
				}
				
				for(Body item:bodyBSari)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx1 = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx1 = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy1 = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy1 = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx1,artisy1);
					}
				}		
					
			}
			

			@Override
			public void reset() 
			{
				
			}
			
		});
		
		this.sceneOyun.attachChild(cTexGoster);
		//////////////////////////////////////
		this.sceneOyun.attachChild(cTexKirmizi);
		this.sceneOyun.attachChild(cTexSari);
		this.sceneOyun.attachChild(cTexSiyah);
		this.sceneOyun.attachChild(cTexYesil);
		////////////////////////////////////
		this.sceneOyun.attachChild(dy1);
		this.sceneOyun.attachChild(dd1);
		this.sceneOyun.attachChild(dd2);
		this.sceneOyun.attachChild(dy2);
		////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(physicsWorld);
		
		/////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(new TimerHandler(30.0f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// TODO Auto-generated method stub
				
				//MainActivity.this.sceneOyun.clearChildScene();
				//MainActivity.this.sceneOyun.clearEntityModifiers();
				MainActivity.this.sceneOyun.reset();
				//////////////////////////////////////////////
				if(skorBolum1 < 100)
				{
					spriteSonuc = new Sprite(0, 0, MainActivity.this.texRegSonuc);
					
					int toplam = skorBolum1+skorBolum2+skorBolum3;
					
					cTexSkor.setText("BOLUM PUANLARI");
					CTexBolum1.setText("BOLUM1: " + MainActivity.this.skorBolum1);
					CTexBolum2.setText("BOLUM2: " + MainActivity.this.skorBolum2);
					CTexBolum3.setText("BOLUM3: " + MainActivity.this.skorBolum3);
					cTexToplam.setText("TOPLAM: " + toplam);
					
					cTexSkor.setColor(0, 1.0f, 0);
					CTexBolum1.setColor(0,1.0f,0);
					CTexBolum2.setColor(0,1.0f,0);
					CTexBolum3.setColor(0,1.0f,0);
					cTexToplam.setColor(0,1.0f,0);
					
					MainActivity.this.sceneOyun.attachChild(spriteSonuc);
					MainActivity.this.sceneOyun.attachChild(cTexSkor);
					MainActivity.this.sceneOyun.attachChild(CTexBolum1);
					MainActivity.this.sceneOyun.attachChild(CTexBolum2);
					MainActivity.this.sceneOyun.attachChild(CTexBolum3);
					MainActivity.this.sceneOyun.attachChild(cTexToplam);
				}
				else if(skorBolum1 >= 100 && (ps1>0 && ps2>0 && ps3>0 && ps4>0) )
				{
					BolumOlustur2();
				}
				///////////////////////////////////////////////
				
			}
			
		}));


		
	}
	
	
	private void BolumOlustur2()
	{
		ps1=0 ; ps2=0 ; ps3=0 ; ps4=0;
		MainActivity.this.sceneOyun.clearUpdateHandlers();
		MainActivity.this.sceneOyun.clearChildScene();
		MainActivity.this.sceneOyun.clearTouchAreas();
		spriteArka = new  Sprite(0, 0, this.texRegArka2);
		this.sceneOyun.attachChild(spriteArka);
		Line dy2,dd1,dd2,dc1,dc2;
		Body bodyDd1, bodyDd2,bodyDc1,bodyDc2,bodyDy2;
		/////////////////////////////////////////
		dc1 = new Line(10,420,30+225,400-120,100.0f);
		dc1.setColor(1.0f, 0, 0.0f);
		
		dc2 = new Line(30+225,400-120,500,400+20,100.0f);
		dc2.setColor(1.0f, 0, 0.0f);
		
		dd1 = new Line(30+450,400,30+450,400+500,100.0f);
		dd1.setColor(1.0f, 0, 0.0f);
		
		
		dy2 = new Line(30+450,400+500,30,400+500,100.0f);
		dy2.setColor(1.0f, 0, 0.0f);
		
		
		dd2 = new Line(30,400+500, 30,400,50);
		dd2.setColor(1.0f, 0.0f, 0.0f);
		///////////////////////////////////////////////////////
		spriteBalonK.clear();
		spriteBalonSari.clear();
		spriteBalonSiyah.clear();
		spriteBalonY.clear();
		//////////////////////////////////////////////////////
		for(int i=0;i<6;i++)
		{
			bodyBk.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBk.get(i));
			///////////////////////////////////
			bodyBy.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBy.get(i));
			//////////////////////////////////
			bodyBSari.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBSari.get(i));
			//////////////////////////////////////////
			bodyBSiyah.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBSiyah.get(i));
		}
		bodyBk.clear();
		bodyBy.clear();
		bodyBSari.clear();
		bodyBSiyah.clear();
		////////////////////////////////////////////////////
		this.sceneOyun.setOnSceneTouchListener(new IOnSceneTouchListener() 
		{
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
			{
				// TODO Auto-generated method stub
				for(MySprite item:spriteBalonK)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici2 = item;
					}
				}
				for(MySprite item:spriteBalonY)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici2 = item;
					}
				}
				for(MySprite item:spriteBalonSari)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici2 = item;
					}
				}
				for(MySprite item:spriteBalonSiyah)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici2 = item;
					}
				}
				if(pSceneTouchEvent.isActionUp())
				{
					if(gecici2!=null)
					{
						
						 gecici2.aktif=false;
				 		 gecici2.detachSelf();
				 		 if(gecici2.R.equals("K"))
				 		 {
				 			 spriteBalonK.remove(gecici2);
				 			 skorBolum2 = skorBolum2 + 10;
		                     ps1++;
		                     ///
		                     bodyBk.get(gecici2.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBk.get(gecici2.sira));
							 //bodyBk.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici2 = null;
				 		 }
				 		 else if(gecici2.R.equals("Y"))
				 		 {
				 			spriteBalonY.remove(gecici2);
				 			skorBolum2 = skorBolum2 + 5;
				 			ps2++;
				 			///
		                     bodyBy.get(gecici2.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBy.get(gecici2.sira));
							 //bodyBy.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici2 = null;
				 		 }
				 		 else if(gecici2.R.equals("Sari"))
				 		 {
				 			spriteBalonSari.remove(gecici2);
				 			skorBolum2 = skorBolum2 + 20;
				 			ps3++;
				 			///
		                     bodyBSari.get(gecici2.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSari.get(gecici2.sira));
							 //bodyBSari.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici2 = null;
				 		 }
				 		 else if(gecici2.R.equals("Siyah"))
				 		 {
				 			spriteBalonSiyah.remove(gecici2);
				 			skorBolum2 = skorBolum2 - 10;
				 			ps4++;
				 			///
		                     bodyBSiyah.get(gecici2.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSiyah.get(gecici2.sira));
							 //bodyBSiyah.remove(gecici.sira);
		                     ///
							 if(patlatSiyah != null)
					 		 {
								 patlatSiyah.play();
					 		 }
							 gecici2 = null;
				 		 }
					}
			 		 
				}
				return false;
			}
		});
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,400,this.texRegBalonK,0,true,"K",i);
			spriteBalonK.add(item);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,500,this.texRegBalonY,0,true,"Y",i);
			item.setColor(0, 1.0f, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonY.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,600,this.texRegBalonSari,0,true,"Sari",i);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSari.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,700,this.texRegBalonSiyah,0,true,"Siyah",i);
			item.setColor(0, 0, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSiyah.add(item);
		}
		
		/////////////////////////////////////////			
		/////////////////////////////////////////
		////////////////////////////////////////
		
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonK.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld2,spriteBalonK.get(i).getX() + 64,spriteBalonK.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(spriteBalonK.get(i), item, true, false));
				item.setLinearVelocity(80,10);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBk.add(item);
			}
	
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonY.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld2,spriteBalonY.get(i).getX() + 64,spriteBalonY.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(spriteBalonY.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBy.add(item);
			}
			
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonSari.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld2,spriteBalonSari.get(i).getX() + 64,spriteBalonSari.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(spriteBalonSari.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBSari.add(item);
			}
			
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonSiyah.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld2,spriteBalonSiyah.get(i).getX() + 64,spriteBalonSiyah.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(spriteBalonSiyah.get(i), item, true, false));
				item.setLinearVelocity(-90,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBSiyah.add(item);
			}
			
		}
		//////////////////////////////////////////////////////////////////////////
		bodyDc1 = PhysicsFactory.createLineBody(this.physicsWorld2, dc1, fixDef);
		bodyDc2 = PhysicsFactory.createLineBody(this.physicsWorld2, dc2, fixDef);
		bodyDy2 = PhysicsFactory.createLineBody(this.physicsWorld2, dy2, fixDef);
		bodyDd1 = PhysicsFactory.createLineBody(this.physicsWorld2, dd1, fixDef);
		bodyDd2 = PhysicsFactory.createLineBody(this.physicsWorld2, dd2, fixDef);
		/////////////////////////////////////////////
		this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(dc1, bodyDc1, false, false));
		this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(dc2, bodyDc2, false, false));
		this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(dd1, bodyDd1, false, false));
		this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(dd2, bodyDd2, false, false));
		this.physicsWorld2.registerPhysicsConnector(new PhysicsConnector(dy2, bodyDy2, false, false));
		////////////////////////////////////////////////////////
		this.engine.registerUpdateHandler(timergorunmezlik = new TimerHandler(1.0f,true,new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				
				
				for(final Sprite item:spriteBalonSari)
				{
					if(item.isVisible())
					{
						item.setVisible(false);
					}
					else
					{
						item.setVisible(true);
					}
				}
				
				
			}
		}));
		 
		 
		 Random r = new Random();
		 float deger = r.nextFloat() % 10 +1;
		 this.engine.registerUpdateHandler(timerrenkDegis = new TimerHandler(deger,true,new ITimerCallback() {
				
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					
					
					for(final MySprite item:spriteBalonSiyah)
					{
						if(item.durum==0)
						{
							item.setColor(0, 1.0f, 0);
							item.durum = 1;
						}
						else if(item.durum == 1)
						{
							item.setColor(0.0f,0.0f, 0.0f);
							item.durum = 0;
						}
					}
					
					
				}
			}));
		 ////////////////////////////////////////////
		
		 
		//////////////////////////////////////////////
		
		this.sceneOyun.registerUpdateHandler(new IUpdateHandler() {
			
			float artisx=0,artisy=0,artisx1=0,artisy1=0;
			@Override
			public void onUpdate(float pSecondsElapsed) 
			{
				if(!muzik.isPlaying())
				{
					muzik.play();
				}
				cTexGoster.setText("Puan: " + skorBolum2);
				cTexKirmizi.setColor(1.0f, 0, 0);
				cTexYesil.setColor(0, 1.0f, 0);
				cTexSari.setColor(255.0f/230.0f, 255.0f/255.0f, 255.0f/10.f);
				cTexSiyah.setColor(0, 0, 0);
				cTexKirmizi.setText(""+ps1);
				cTexYesil.setText(""+ps2);
				cTexSari.setText(""+ps3);
				cTexSiyah.setText(""+ps4);
				for(Body item:bodyBSiyah)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}							
					}
					
				}
				for(Body item:bodyBy)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}
						
					}
				}
				
				for(Body item:bodyBk)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx,artisy);
					}
				}
				
				for(Body item:bodyBSari)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx1 = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx1 = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy1 = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy1 = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx1,artisy1);
					}
				}		
					
			}
			

			@Override
			public void reset() 
			{
				
			}
			
		});
		cTexGoster.setColor(1f,0,0);
		///////////////////////////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(physicsWorld2);
		this.sceneOyun.attachChild(dc1);
		this.sceneOyun.attachChild(dc2);
		this.sceneOyun.attachChild(dd1);
		this.sceneOyun.attachChild(dd2);
		this.sceneOyun.attachChild(dy2);
		this.sceneOyun.attachChild(cTexGoster);
		this.sceneOyun.attachChild(cTexKirmizi);
		this.sceneOyun.attachChild(cTexSari);
		this.sceneOyun.attachChild(cTexSiyah);
		this.sceneOyun.attachChild(cTexYesil);
		/////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(new TimerHandler(30.0f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// TODO Auto-generated method stub
				
				
				MainActivity.this.sceneOyun.clearChildScene();
				MainActivity.this.sceneOyun.clearEntityModifiers();;
				//////////////////////////////////////////////
				if(skorBolum2 < 100)
				{
					spriteSonuc = new Sprite(0, 0, MainActivity.this.texRegSonuc);
					
					int toplam = skorBolum1+skorBolum2+skorBolum3;
					
					cTexSkor.setText("BOLUM PUANLARI");
					CTexBolum1.setText("BOLUM1: " + MainActivity.this.skorBolum1);
					CTexBolum2.setText("BOLUM2: " + MainActivity.this.skorBolum2);
					CTexBolum3.setText("BOLUM3: " + MainActivity.this.skorBolum3);
					cTexToplam.setText("TOPLAM: " + toplam);
					
					cTexSkor.setColor(0, 1.0f, 0);
					CTexBolum1.setColor(0,1.0f,0);
					CTexBolum2.setColor(0,1.0f,0);
					CTexBolum3.setColor(0,1.0f,0);
					cTexToplam.setColor(0,1.0f,0);
					
					MainActivity.this.sceneOyun.attachChild(spriteSonuc);
					MainActivity.this.sceneOyun.attachChild(cTexSkor);
					MainActivity.this.sceneOyun.attachChild(CTexBolum1);
					MainActivity.this.sceneOyun.attachChild(CTexBolum2);
					MainActivity.this.sceneOyun.attachChild(CTexBolum3);
					MainActivity.this.sceneOyun.attachChild(cTexToplam);
				}
				else if(skorBolum2 >= 100 && (ps1>0 && ps2>0 && ps3>0 && ps4>0) )
				{
					BolumOlustur3();
				}
				///////////////////////////////////////////////
				
			}
			
		}));
		////////////////////////////////////////////////////////////
		
	}
	private void BolumOlustur3()
	{
		ps1=0 ; ps2=0 ; ps3=0 ; ps4=0;
		MainActivity.this.sceneOyun.clearUpdateHandlers();
		MainActivity.this.sceneOyun.clearChildScene();
		MainActivity.this.sceneOyun.clearTouchAreas();
		spriteArka = new  Sprite(0, 0, this.texRegArka3);
		this.sceneOyun.attachChild(spriteArka);
		Line dd1,dd2,dc1,dc2,dc3,dc4;
		Body bodyDd1, bodyDd2,bodyDc1,bodyDc2,bodyDc3,bodyDc4;
		/////////////////////////////////////////
		dc1 = new Line(10,420,30+225,400-120,100.0f);
		dc1.setColor(1.0f, 0, 0.0f);
		
		dc2 = new Line(30+225,400-120,500,400+20,100.0f);
		dc2.setColor(1.0f, 0, 0.0f);
		
		dd1 = new Line(30+450,400,30+450,400+500,100.0f);
		dd1.setColor(1.0f, 0, 0.0f);
		
		dc3 = new Line(10,880,30+225,900+120,100.0f);
		dc3.setColor(1.0f, 0, 0.0f);
		
		dc4 = new Line(30+255,900+120,500,880,100.0f);
		dc4.setColor(1.0f, 0, 0.0f);
		//dy2 = new Line(30+450,400+500,30,400+500,100.0f);
		//dy2.setColor(1.0f, 0, 0.0f);
		
		
		dd2 = new Line(30,400+500, 30,400,50);
		dd2.setColor(1.0f, 0.0f, 0.0f);
		///////////////////////////////////////////////////////
		spriteBalonK.clear();
		spriteBalonSari.clear();
		spriteBalonSiyah.clear();
		spriteBalonY.clear();
		//////////////////////////////////////////////////////
	
		for(int i=0;i<elemanSayisi;i++)
		{
			bodyBk.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBk.get(i));
			///////////////////////////////////
			bodyBy.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBy.get(i));
			//////////////////////////////////
			bodyBSari.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBSari.get(i));
			//////////////////////////////////////////
			bodyBSiyah.get(i).setActive(false);
			physicsWorld.destroyBody(bodyBSiyah.get(i));
		}
		bodyBk.clear();
		bodyBy.clear();
		bodyBSari.clear();
		bodyBSiyah.clear();
		////////////////////////////////////////////////////
		this.sceneOyun.setOnSceneTouchListener(new IOnSceneTouchListener() 
		{
			@Override
			public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
			{
				// TODO Auto-generated method stub
				for(MySprite item:spriteBalonK)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici3 = item;
					}
				}
				for(MySprite item:spriteBalonY)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici3 = item;
					}
				}
				for(MySprite item:spriteBalonSari)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici3 = item;
					}
				}
				for(MySprite item:spriteBalonSiyah)
				{
					if(( item.getX()<pSceneTouchEvent.getX() && item.getX() + 64 >= pSceneTouchEvent.getX() ) && (item.getY()<pSceneTouchEvent.getY() && item.getY() + 64 >= pSceneTouchEvent.getY()))
					{
						gecici3 = item;
					}
				}
				if(pSceneTouchEvent.isActionUp())
				{
					if(gecici3!=null)
					{
						
						 gecici3.aktif=false;
				 		 gecici3.detachSelf();
				 		 if(gecici3.R.equals("K"))
				 		 {
				 			 spriteBalonK.remove(gecici3);
				 			 skorBolum3 = skorBolum3 + 10;
		                     ps1++;
		                     ///
		                     bodyBk.get(gecici3.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBk.get(gecici3.sira));
							 //bodyBk.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici3 = null;
				 		 }
				 		 else if(gecici3.R.equals("Y"))
				 		 {
				 			spriteBalonY.remove(gecici3);
				 			skorBolum3 = skorBolum3 + 5;
				 			ps2++;
				 			///
		                     bodyBy.get(gecici3.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBy.get(gecici3.sira));
							 //bodyBy.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici3 = null;
				 		 }
				 		 else if(gecici3.R.equals("Sari"))
				 		 {
				 			spriteBalonSari.remove(gecici3);
				 			skorBolum3 = skorBolum3 + 20;
				 			ps3++;
				 			///
		                     bodyBSari.get(gecici3.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSari.get(gecici3.sira));
							 //bodyBSari.remove(gecici.sira);
		                     ///
							 if(patlat != null)
					 		 {
					 			 patlat.play();
					 		 }
							 gecici3 = null;
				 		 }
				 		 else if(gecici3.R.equals("Siyah"))
				 		 {
				 			spriteBalonSiyah.remove(gecici3);
				 			skorBolum3 = skorBolum3 - 10;
				 			ps4++;
				 			///
		                     bodyBSiyah.get(gecici3.sira).setActive(false);
							 physicsWorld.destroyBody(bodyBSiyah.get(gecici3.sira));
							 //bodyBSiyah.remove(gecici.sira);
		                     ///
							 if(patlatSiyah != null)
					 		 {
								 patlatSiyah.play();
					 		 }
							 gecici3 = null;
				 		 }
					}
			 		 
				}
				return false;
			}
		});
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,400,this.texRegBalonK,0,true,"K",i);
			spriteBalonK.add(item);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,500,this.texRegBalonY,0,true,"Y",i);
			item.setColor(0, 1.0f, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonY.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,600,this.texRegBalonSari,0,true,"Sari",i);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSari.add(item);
		}
		for(i=0;i<elemanSayisi;i++)
		{
			MySprite item = new MySprite(30 + 70*i,700,this.texRegBalonSiyah,0,true,"Siyah",i);
			item.setColor(0, 0, 0);
			this.sceneOyun.registerTouchArea(item);
			this.sceneOyun.attachChild(item);
			spriteBalonSiyah.add(item);
		}
		/////////////////////////////////////////			
		/////////////////////////////////////////
		////////////////////////////////////////
		
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonK.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld3,spriteBalonK.get(i).getX() + 64,spriteBalonK.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(spriteBalonK.get(i), item, true, false));
				item.setLinearVelocity(80,10);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBk.add(item);
			}
	
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonY.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld3,spriteBalonY.get(i).getX() + 64,spriteBalonY.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(spriteBalonY.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBy.add(item);
			}
			
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonSari.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld3,spriteBalonSari.get(i).getX() + 64,spriteBalonSari.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(spriteBalonSari.get(i), item, true, false));
				item.setLinearVelocity(0,20);
				MassData kucuk = item.getMassData();
				kucuk.mass = 20;
				item.setMassData(kucuk);
				bodyBSari.add(item);
			}
			
		}
		for(int i=0;i<elemanSayisi;i++)
		{
			if(spriteBalonSiyah.get(i).aktif)
			{
				Body item = PhysicsFactory.createCircleBody(physicsWorld3,spriteBalonSiyah.get(i).getX() + 64,spriteBalonSiyah.get(i).getY() + 64, 32, 0,BodyType.DynamicBody, fixDef);
				this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(spriteBalonSiyah.get(i), item, true, false));
				item.setLinearVelocity(-90,20);
				MassData buyuk = item.getMassData();
				buyuk.mass = 2000;
				item.setMassData(buyuk);
				bodyBSiyah.add(item);
			}
			
		}
		//////////////////////////////////////////////////////////////////////////
		bodyDc1 = PhysicsFactory.createLineBody(this.physicsWorld3, dc1, fixDef);
		bodyDc2 = PhysicsFactory.createLineBody(this.physicsWorld3, dc2, fixDef);
		bodyDc3 = PhysicsFactory.createLineBody(this.physicsWorld3, dc3, fixDef);
		bodyDc4 = PhysicsFactory.createLineBody(this.physicsWorld3, dc4, fixDef);
		
		bodyDd1 = PhysicsFactory.createLineBody(this.physicsWorld3, dd1, fixDef);
		bodyDd2 = PhysicsFactory.createLineBody(this.physicsWorld3, dd2, fixDef);
		/////////////////////////////////////////////
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dc1, bodyDc1, false, false));
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dc2, bodyDc2, false, false));
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dd1, bodyDd1, false, false));
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dd2, bodyDd2, false, false));
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dc3, bodyDc3, false, false));
		this.physicsWorld3.registerPhysicsConnector(new PhysicsConnector(dc4, bodyDc4, false, false));
		////////////////////////////////////////////////////////
		this.engine.registerUpdateHandler(timergorunmezlik = new TimerHandler(1.0f,true,new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				
				
				for(final Sprite item:spriteBalonSari)
				{
					if(item.isVisible())
					{
						item.setVisible(false);
					}
					else
					{
						item.setVisible(true);
					}
				}
				
				
			}
		}));
		 
		 
		 Random r = new Random();
		 float deger = r.nextFloat() % 10 +1;
		 this.engine.registerUpdateHandler(timerrenkDegis = new TimerHandler(deger,true,new ITimerCallback() {
				
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					
					
					for(final MySprite item:spriteBalonSiyah)
					{
						if(item.durum==0)
						{
							item.setColor(0, 1.0f, 0);
							item.durum = 1;
						}
						else if(item.durum == 1)
						{
							item.setColor(0.0f,0.0f, 0.0f);
							item.durum = 0;
						}
					}
					
					
				}
			}));
		 ////////////////////////////////////////////
		
		 
		//////////////////////////////////////////////
		
		this.sceneOyun.registerUpdateHandler(new IUpdateHandler() {
			
			float artisx=0,artisy=0,artisx1=0,artisy1=0;
			@Override
			public void onUpdate(float pSecondsElapsed) 
			{
				if(!muzik.isPlaying())
				{
					muzik.play();
				}
				cTexGoster.setText("Puan: " + skorBolum3);
				cTexKirmizi.setColor(1.0f, 0, 0);
				cTexYesil.setColor(0, 1.0f, 0);
				cTexSari.setColor(255.0f/230.0f, 255.0f/255.0f, 255.0f/10.f);
				cTexSiyah.setColor(0, 0, 0);
				cTexKirmizi.setText(""+ps1);
				cTexYesil.setText(""+ps2);
				cTexSari.setText(""+ps3);
				cTexSiyah.setText(""+ps4);
				for(Body item:bodyBSiyah)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}							
					}
					
				}
				for(Body item:bodyBy)
				{
					if(Math.abs(item.getLinearVelocity().y) < 15)
					{
						
						if(item.getLinearVelocity().y < 15 && item.getLinearVelocity().y > 0)
						{
							item.setLinearVelocity(0,20);
						}
						else
						{
							item.setLinearVelocity(0,-20);
						}
						
					}
				}
				
				for(Body item:bodyBk)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx,artisy);
					}
				}
				
				for(Body item:bodyBSari)
				{
					if(Math.sqrt((Math.pow(item.getLinearVelocity().x,2) + Math.pow(item.getLinearVelocity().y,2))) < 8)
					{
						
						if(item.getLinearVelocity().x < 0 )
						{
							artisx1 = item.getLinearVelocity().x - 10;
						}
						else
						{
							artisx1 = item.getLinearVelocity().x + 10;
						}
						
						if(item.getLinearVelocity().y < 0 )
						{
							artisy1 = item.getLinearVelocity().y - 10;
						}
						else
						{
							artisy1 = item.getLinearVelocity().y + 10;
						}
						item.setLinearVelocity(artisx1,artisy1);
					}
				}		
					
			}
			

			@Override
			public void reset() 
			{
				
			}
			
		});
		cTexGoster.setColor(1f,0,0);
		///////////////////////////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(physicsWorld3);
		this.sceneOyun.attachChild(dc1);
		this.sceneOyun.attachChild(dc2);
		this.sceneOyun.attachChild(dd1);
		this.sceneOyun.attachChild(dd2);
		this.sceneOyun.attachChild(dc3);
		this.sceneOyun.attachChild(dc4);
		this.sceneOyun.attachChild(cTexGoster);
		this.sceneOyun.attachChild(cTexKirmizi);
		this.sceneOyun.attachChild(cTexSari);
		this.sceneOyun.attachChild(cTexSiyah);
		this.sceneOyun.attachChild(cTexYesil);
		/////////////////////////////////////
		this.sceneOyun.registerUpdateHandler(new TimerHandler(30.0f, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// TODO Auto-generated method stub
				
				MainActivity.this.sceneOyun.clearChildScene();
				MainActivity.this.sceneOyun.clearEntityModifiers();;
				//////////////////////////////////////////////
				spriteSonuc = new Sprite(0, 0, MainActivity.this.texRegSonuc);
				int toplam = skorBolum1+skorBolum2+skorBolum3;
					
				cTexSkor.setText("BOLUM PUANLARI");
				CTexBolum1.setText("BOLUM1: " + MainActivity.this.skorBolum1);
				CTexBolum2.setText("BOLUM2: " + MainActivity.this.skorBolum2);
				CTexBolum3.setText("BOLUM3: " + MainActivity.this.skorBolum3);
				cTexToplam.setText("TOPLAM: " + toplam);
					
				cTexSkor.setColor(0, 1.0f, 0);
				CTexBolum1.setColor(0,1.0f,0);
				CTexBolum2.setColor(0,1.0f,0);
				CTexBolum3.setColor(0,1.0f,0);
				cTexToplam.setColor(0,1.0f,0);
					
				MainActivity.this.sceneOyun.attachChild(spriteSonuc);
				MainActivity.this.sceneOyun.attachChild(cTexSkor);
				MainActivity.this.sceneOyun.attachChild(CTexBolum1);
				MainActivity.this.sceneOyun.attachChild(CTexBolum2);
				MainActivity.this.sceneOyun.attachChild(CTexBolum3);
				MainActivity.this.sceneOyun.attachChild(cTexToplam);
				///////////////////////////////////////////////
			}
			
		}));
		////////////////////////////////////////////////////////////
		
	}
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}
	
}

