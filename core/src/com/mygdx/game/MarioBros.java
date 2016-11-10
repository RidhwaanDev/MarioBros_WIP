package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.utils.DistributionAdapters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;

public class MarioBros extends Game {

	public static final int V_WIDTH = 400;
	public  static  final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTRYOED_BIT = 16;
	public static final short BULLET_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;


	public SpriteBatch batch;

	public static AssetManager manager;

	@Override
	public void create () {

		batch= new SpriteBatch();
		manager = new AssetManager();
//
		//manager.load( "gameMusic.mp3",  Music.class);
//		manager.load("Fart_sound_effect.mp3", Sound.class);
//		manager.load("wtf.mp3", Sound.class);
		manager.finishLoading();







		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {


		manager.update();
		super.render();

	}
}
