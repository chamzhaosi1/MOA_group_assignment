# MOA_group_assignment

# Note for CRUD Firestore Database

## To get user id
```kotlin
 val uid:String = FirebaseAuth.getInstance().currentUser!!.uid;
```

## To set the document 
```kotlin
mDB.collection("Users").document(uid)
```

## To create the collection with own document 
```kotlin
mDB.collection("Users").document(uid)
            .set(user)
            .addOnCompleteListener {
                Toast.makeText(this@Dashboard, "record added successfully", Toast.LENGTH_SHORT).show()
            }
```

