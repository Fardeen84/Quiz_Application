package com.example.computerknowledge

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.RatingBar
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Rating_dailog : Dialog {
    constructor(context: Context) : super(context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rating_dilog)
        val yes=findViewById<MaterialButton>(R.id.Ratingbuten)
        val cancel=findViewById<MaterialButton>(R.id.cancel)
        val ratingbar=findViewById<RatingBar>(R.id.rating)
         var rating_image =findViewById<ImageView>(R.id.image_imogi)

        var reference=FirebaseDatabase.getInstance().getReference()
        val auth=FirebaseAuth.getInstance()
        var uid=auth.uid.toString()


        ratingbar.numStars=5
        ratingbar.stepSize=1f
        ratingbar.rating=3f

        ratingbar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            if (rating<=1)
            {
                rating_image.setImageResource(R.drawable.fist_icon)
            }
           else if (rating<=2)
            {
                rating_image.setImageResource(R.drawable.second_icon)
            }
          else  if (rating<=3)
            {
                rating_image.setImageResource(R.drawable.third_icon)
            }
         else   if (rating<=4)
            {
                rating_image.setImageResource(R.drawable.forth_icon)
            }
            else{
                rating_image.setImageResource(R.drawable.love)
            }
            anim(rating_image)
        }


        yes.setOnClickListener {
            var rat=ratingbar.rating.toString()

            reference.child("User").child(uid).child("Rating").setValue(rat)
                //Toast.makeText(context,"Rating now",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        cancel.setOnClickListener {
            dismiss()
        }




    }

    fun anim(imageView: ImageView)
    {

        var scaleAnimation=ScaleAnimation(0F,1f,
            0F,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)

        scaleAnimation.fillAfter=true
        scaleAnimation.duration=200

        imageView.startAnimation(scaleAnimation)

    }
}