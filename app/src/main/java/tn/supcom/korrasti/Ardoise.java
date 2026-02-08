package tn.supcom.korrasti;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class Ardoise extends GraphicsActivity {

    public static int DEFAULT_BRUSH_SIZE = 10;
    private static int MAX_POINTERS = 10;
    private static final float TOUCH_TOLERANCE = 4;

    private ImageButton chiffon;
    private Bitmap bitmap;
    private Paint mPaint;
    private boolean doubleBackToExitPressedOnce = false;
    private MyView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // it removes the title from the actionbar(more space for icons?)
        // this.getActionBar().setDisplayShowTitleEnabled(false);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        contentView = new MyView( this );
        setContentView( contentView );

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DEFAULT_BRUSH_SIZE);
        chiffon=(ImageButton)findViewById(R.id.chiffon);
       /* chiffon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }

    public class MyView extends View {

        public Bitmap mBitmap;
        private Bitmap mBitmapBackground;
        private Canvas mCanvas;
        private Paint mBitmapPaint;
        private MultiLinePathManager multiLinePathManager;

        private class LinePath extends Path {
            private Integer idPointer;
            private float lastX;
            private float lastY;

            LinePath() {
                this.idPointer = null;
            }

            public float getLastX() {
                return lastX;
            }

            public float getLastY() {
                return lastY;
            }

            public void touchStart(float x, float y) {
                this.reset();
                this.moveTo(x, y);
                this.lastX = x;
                this.lastY = y;
            }

            public void touchMove(float x, float y) {
                float dx = Math.abs(x - lastX);
                float dy = Math.abs(y - lastY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    this.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2);
                    lastX = x;
                    lastY = y;
                }
            }

            public boolean isDisassociatedFromPointer() {
                return idPointer == null;
            }

            public boolean isAssociatedToPointer(int idPointer) {
                return this.idPointer != null
                        && (int) this.idPointer == idPointer;
            }

            public void disassociateFromPointer() {
                idPointer = null;
            }

            public void associateToPointer(int idPointer) {
                this.idPointer = idPointer;
            }
        }

        private class MultiLinePathManager {
            public LinePath[] superMultiPaths;

            MultiLinePathManager(int maxPointers) {
                superMultiPaths = new LinePath[maxPointers];
                for (int i = 0; i < maxPointers; i++) {
                    superMultiPaths[i] = new LinePath();
                }
            }

            public LinePath findLinePathFromPointer(int idPointer) {
                for (LinePath superMultiPath : superMultiPaths) {
                    if (superMultiPath.isAssociatedToPointer(idPointer)) {
                        return superMultiPath;
                    }
                }
                return null;
            }

            public LinePath addLinePathWithPointer(int idPointer) {
                for (LinePath superMultiPath : superMultiPaths) {
                    if (superMultiPath.isDisassociatedFromPointer()) {
                        superMultiPath.associateToPointer(idPointer);
                        return superMultiPath;
                    }
                }
                return null;
            }
        }

        public MyView(Context c) {
            super(c);

            setId(R.id.CanvasId);
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chiffon);
            //Bitmap sized = Bitmap.createScaledBitmap(bitmap, size.x, size.y, true);
            //bitmap= sized;
            //chiffon.setImageBitmap(bitmap);
            //Drawable drawable=getDrawable(R.drawable.maitrehibou);
            mBitmapBackground = BitmapFactory.decodeResource(getResources(), R.drawable.maitrehibou);
            Bitmap resized = Bitmap.createScaledBitmap(mBitmapBackground, size.x, size.y, true);
            mBitmapBackground = resized;
            mBitmap = Bitmap.createBitmap(size.x,size.y,Bitmap.Config.ARGB_8888);
            //drawable.setBounds(0, 0, 0,800);
            mCanvas = new Canvas(mBitmap);
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            multiLinePathManager = new MultiLinePathManager(MAX_POINTERS);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap( mBitmapBackground,0, 0, new Paint() );
            // canvas.drawBitmap(bitmap,940,0,new Paint());
            canvas.drawBitmap( mBitmap,0,0 ,mBitmapPaint );
            for (int i = 0; i < multiLinePathManager.superMultiPaths.length; i++) {
                canvas.drawPath(multiLinePathManager.superMultiPaths[i], mPaint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            LinePath linePath;
            int index;
            int id;
            int eventMasked = event.getActionMasked();
            switch (eventMasked) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    index = event.getActionIndex( );
                    id = event.getPointerId( index );
                    View v = findViewById(R.id.CanvasId);
                    v.setDrawingCacheEnabled(true);
                    v.destroyDrawingCache();
                    linePath = multiLinePathManager.addLinePathWithPointer( id );
                    if( linePath != null ) {
                        linePath.touchStart( event.getX( index ), event.getY( index ) );
                    } else {
                        Log.e( "anupam", "Too many fingers!" );
                    }

                    break;
                }
                case MotionEvent.ACTION_MOVE:
                    for (int i = 0; i < event.getPointerCount(); i++) {
                        id = event.getPointerId(i);
                        index = event.findPointerIndex(id);
                        linePath = multiLinePathManager.findLinePathFromPointer(id);
                        if (linePath != null) {
                            linePath.touchMove(event.getX(index), event.getY(index));
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    index = event.getActionIndex();
                    id = event.getPointerId(index);
                    linePath = multiLinePathManager.findLinePathFromPointer(id);
                    if (linePath != null) {
                        linePath.lineTo(linePath.getLastX(), linePath.getLastY());

                        // Commit the path to our offscreen
                        mCanvas.drawPath(linePath, mPaint);

                        // Kill this so we don't double draw
                        linePath.reset();

                        // Allow this LinePath to be associated to another idPointer
                        linePath.disassociateFromPointer();
                    }
                    break;
            }
            invalidate();
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_all:
                contentView.mBitmap.eraseColor( Color.TRANSPARENT );
                return true;
            case R.id.normal_brush:
                mPaint.setShader( null );
                mPaint.setMaskFilter(null);
                return true;
            case R.id.brush:
                mPaint.setStrokeWidth( 100 );
                mPaint.setShader( null );
                mPaint.setXfermode( new PorterDuffXfermode( PorterDuff.Mode.CLEAR ) );
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}