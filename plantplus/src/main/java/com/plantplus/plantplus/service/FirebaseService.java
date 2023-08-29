package com.plantplus.plantplus.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import com.plantplus.plantplus.dto.plantUser.UserDto;
import com.plantplus.plantplus.dto.plantUser.UserPlantDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseService {

    // firestore
    public static final String COLLECTION_NAME = "User";

    // user CRUD
    public String insertUser(UserDto userDto) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(userDto.getId()).set(userDto);
        return apiFuture.get().getUpdateTime().toString();
    }

    public List<UserDto> getUserList() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection(COLLECTION_NAME).get();

        List<UserDto> userDtoList = new ArrayList<>();
        for (QueryDocumentSnapshot document : apiFuture.get()){
            UserDto userDto = null;
            if(document.exists()){
                userDto = document.toObject(UserDto.class);
                System.out.println(userDto.getId()+" : full name"+userDto.getFull_name());
                userDtoList.add(userDto);
            }
            else{
                System.out.println("null");
                return null;
            }
        }
        return userDtoList;
    }

    public UserDto getUserDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        UserDto userDto = null;
        if(documentSnapshot.exists()){
            userDto = documentSnapshot.toObject(UserDto.class);
            System.out.println(userDto.getId()+" : full name"+userDto.getFull_name());
            return userDto;
        }
        else{
            System.out.println("null");
            return null;
        }
    }

    public String updateUser(UserDto userDto) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(userDto.getId()).set(userDto);
        return apiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(id).delete();
        return "Document id: "+id+" delete";
    }

    // 하위 plant 컬렉션에 새로운 plant data를 CRUD
    public static final String PLANT_COLLECTION_NAME = "Plant";
    public String insertPlant(String id, UserPlantDto userPlantDto) throws Exception{
        // 아이디 없으면 임의로 부여한다(현재시간)
        String plantId = userPlantDto.getId();
        if (plantId == null) plantId = Long.toString(System.currentTimeMillis());
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(id)
                        .collection(PLANT_COLLECTION_NAME).document(userPlantDto.getId())
                        .set(userPlantDto);
        return apiFuture.get().getUpdateTime().toString();
    }

    public List<UserPlantDto> getPlantList(String userId) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection(COLLECTION_NAME).document(userId).collection(PLANT_COLLECTION_NAME).get();

        List<UserPlantDto> userPlantDtoListr = new ArrayList<>();
        for (QueryDocumentSnapshot document : apiFuture.get()){
            UserPlantDto userPlantDto = null;
            if(document.exists()){
                userPlantDto = document.toObject(UserPlantDto.class);
                System.out.println(userPlantDto.getId()+" : plant name is"+userPlantDto.getName());
                userPlantDtoListr.add(userPlantDto);
            }
            else{
                System.out.println("null");
                return null;
            }
        }
        return userPlantDtoListr;
    }

    public UserPlantDto getPlantDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        UserPlantDto userPlantDto = null;
        if(documentSnapshot.exists()){
            userPlantDto = documentSnapshot.toObject(UserPlantDto.class);
            System.out.println(userPlantDto.getId()+" : plant name "+userPlantDto.getName());
            return userPlantDto;
        }
        else{
            System.out.println("null");
            return null;
        }
    }

    public String updatePlant(String id, UserPlantDto userPlantDto) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(id)
                .collection(PLANT_COLLECTION_NAME).document(userPlantDto.getId())
                .set(userPlantDto);

        return apiFuture.get().getUpdateTime().toString();
    }

    public String deletePlant(String userId, String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(userId)
                .collection(PLANT_COLLECTION_NAME).document(id)
                .delete();
        return "Document id: "+id+" delete";
    }

    // realtime
    public void makeUser(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");

        DatabaseReference usersRef = ref.child("users");

        Map<String, UserDto> users = new HashMap<>();
        users.put("alanisawesome", new UserDto("1234", "Alan Turing"));
        users.put("gracehop", new UserDto("2345", "Grace Hopper"));

        usersRef.setValueAsync(users);
    }

    public void readUser(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog/users");
        System.out.println("try");


        // 왜 작동이 안 되지??
        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    System.out.println("success0");
                    UserDto userDto = dataSnapshot.getValue(UserDto.class);
                    System.out.println("success");
                    System.out.println(userDto.getFull_name());
                }
                catch (Exception ex) {
                    System.out.println("Exception occurred: " + ex.getMessage());
                    // Handle other exceptions, if needed
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("success0");
                UserDto userDto = dataSnapshot.getValue(UserDto.class);
                System.out.println("success");
                System.out.println(userDto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }


}
