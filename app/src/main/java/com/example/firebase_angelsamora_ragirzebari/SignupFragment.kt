package com.example.firebase_angelsamora_ragirzebari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var root: View
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_signup, container, false)
        db = Firebase.firestore
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root.findViewById<Button>(R.id.confirmSignup).setOnClickListener {
            //Derive user details
            var name = root.findViewById<EditText>(R.id.signupName).text.toString()
            var email = root.findViewById<EditText>(R.id.signupEmail).text.toString()
            var password = root.findViewById<EditText>(R.id.signupPassword).text.toString()
            var confirmPassword = root.findViewById<EditText>(R.id.signupConfirmPassword).text.toString()

            //Output details
            Log.d("SignupFragment", "Name: $name")
            Log.d("SignupFragment", "Email: $email")
            Log.d("SignupFragment", "Password: $password")
            Log.d("SignupFragment", "Confirm password: $confirmPassword")

            if(password.equals(confirmPassword)){
                //Populate map
                val user = buildMap<String, Any> {
                    put("Name", name)
                    put("Email", email)
                    put("Password", password)
                }

                //Add to users collection
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Account Created!", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Account Creation Failed!", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(context, "Incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}