package com.foodie.app.listsDB;

import android.content.ContentValues;
import android.database.Cursor;

import com.foodie.app.database.Converters;
import com.foodie.app.database.IDBManager;
import com.foodie.app.entities.Activity;
import com.foodie.app.entities.Business;
import com.foodie.app.entities.CPUser;
import com.foodie.app.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.foodie.app.entities.User.get_ID;

/**
 * Created by Daniel on 12/13/2016.
 */

public class ListDBManager implements IDBManager {
    static List<User> users;
    static List<CPUser> cpusers;
    static List<Business> businesses;
    static List<Activity> activities;
    boolean isUpdated = false;

    static {
        users = new ArrayList<>();
        cpusers = new ArrayList<>();
        businesses = new ArrayList<>();
        activities = new ArrayList<>();
    }

    @Override
    public int addCPUser(ContentValues values) throws Exception {
        CPUser cpuser = Converters.ContentValuesToCPUser(values);
        Random rand = new Random();
        boolean isExist = false;
        do {
            for (CPUser cp : cpusers) {
                if (cp.get_ID() == cpuser.get_ID()) {
                    isExist = true;
                    cpuser.set_ID(rand.nextInt(100000) + 1);
                    break;
                }
                isExist = false;
            }
        } while (isExist == true);
        cpusers.add(cpuser);
        isUpdated = true;
        return cpuser.get_ID();
    }

    @Override
    public int addBusiness(ContentValues values) throws Exception {
        Business business = Converters.ContentValuesToBusiness(values);
        Random rand = new Random();
        boolean isExist = false;
        do {
            for (CPUser cp : cpusers) {
                if (cp.get_ID() == business.get_ID()) {
                    isExist = true;
                    business.set_ID(rand.nextInt(100000) + 1);
                    break;
                }
                isExist = false;
            }
        } while (isExist == true);
        businesses.add(business);
        isUpdated = true;
        return business.get_ID();
    }

    @Override
    public int addActivity(ContentValues values) throws Exception {
        Activity activity = Converters.ContentValuesToActivity(values);
        Random rand = new Random();
        boolean isExist = false;
        do {
            for (CPUser cp : cpusers) {
                if (cp.get_ID() == activity.get_ID()) {
                    isExist = true;
                    activity.set_ID(rand.nextInt(100000) + 1);
                    break;
                }
                isExist = false;
            }
        } while (isExist == true);
        activities.add(activity);
        isUpdated = true;
        return activity.get_ID();
    }

    @Override
    public int addUser(ContentValues values) throws Exception {
        User user = Converters.ContentValuesToUser(values);
        Random rand = new Random();
        boolean isExist = false;
        do {
            for (CPUser cp : cpusers) {
                if (cp.get_ID() == get_ID()) {
                    isExist = true;
                    user.set_ID(rand.nextInt(100000) + 1);
                    break;
                }
                isExist = false;
            }
        } while (isExist == true);
        users.add(user);
        isUpdated = true;
        return get_ID();
    }

    @Override
    public boolean removeCPUser(long id) throws Exception {
        CPUser cpuserToRemove = null;
        for (CPUser item : cpusers)
            if (item.get_ID() == id) {
                cpuserToRemove = item;
                isUpdated = true;
                break;
            }

        return cpusers.remove(cpuserToRemove);
    }

    @Override
    public boolean removeBusiness(long id) throws Exception {
        Business businessToRemove = null;
        for (Business item : businesses)
            if (item.get_ID() == id) {
                businessToRemove = item;
                isUpdated = true;
                break;
            }

        return businesses.remove(businessToRemove);
    }

    @Override
    public boolean removeActivity(long id) throws Exception {
        Activity activityToRemove = null;
        for (Activity item : activities)
            if (item.get_ID() == id) {
                activityToRemove = item;
                isUpdated = true;
                break;
            }

        return activities.remove(activityToRemove);
    }

    @Override
    public boolean removeUser(long id) throws Exception {
        User userToRemove = null;
        for (User item : users)
            if (get_ID() == id) {
                userToRemove = item;
                isUpdated = true;
                break;
            }

        return users.remove(userToRemove);
    }

    @Override
    public Cursor getCPUser() {
        return Converters.CPUserListToCursor(cpusers);
    }

    @Override
    public Cursor getBusiness() {
        return Converters.BusinessListToCursor(businesses);
    }

    @Override
    public Cursor getActivity() {
        return Converters.ActivitiesListToCursor(activities);
    }

    @Override
    public Cursor getUser() {
        return Converters.UserListToCursor(users);
    }

    @Override
    public boolean updateCPUser(int id, ContentValues values) throws Exception {
        CPUser cpuser = Converters.ContentValuesToCPUser(values);
        for (CPUser cp : cpusers) {
            if (cp.get_ID() == id) {
                cp.set_ID(cpuser.get_ID());
                cp.setUserFullName(cpuser.getUserFullName());
                cp.setUserEmail(cpuser.getUserFullName());
                cp.setUserPwdHash(cpuser.getUserPwdHash());
                isUpdated = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateBusiness(int id, ContentValues values) throws Exception {
        Business business = Converters.ContentValuesToBusiness(values);
        for (Business b : businesses) {
            if (b.get_ID() == id) {
                b.set_ID(business.get_ID());
                b.setBusinessName(business.getBusinessName());
                b.setBusinessAddress(business.getBusinessAddress());
                b.setBusinessPhoneNo(business.getBusinessPhoneNo());
                b.setBusinessEmail(business.getBusinessEmail());
                b.setBusinessWebsite(business.getBusinessWebsite());
                b.setCpuserID(business.getCpuserID());
                b.setBusinessLogo(business.getBusinessLogo());

                isUpdated = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateActivity(int id, ContentValues values) throws Exception {
        Activity activity = Converters.ContentValuesToActivity(values);
        for (Activity a : activities) {
            if (a.get_ID() == id) {
                a.set_ID(activity.get_ID());
                a.setActivityName(activity.getActivityName());
                a.setActivityDate(activity.getActivityDate());
                a.setActivityDescription(activity.getActivityDescription());
                a.setActivityCost(activity.getActivityCost());
                a.setActivityRating(activity.getActivityRating());
                a.setActivityImages(activity.getActivityImages());
                a.setBusinessId(activity.getBusinessId());
                a.setFeature(activity.getFeature());

                isUpdated = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(int id, ContentValues values) throws Exception {
        User user = Converters.ContentValuesToUser(values);
        for (User u : users) {
            if (u.get_ID() == id) {
                u.set_ID(user.get_ID());
                u.setUserPhoneNumber(user.getUserPhoneNumber());
                u.setUserPwdHash(user.getUserPwdHash());
                u.setUserAddress(user.getUserAddress());
                u.setUserFullName(user.getUserFullName());
                u.setUserEmail(user.getUserEmail());
                u.setUserImage(user.getUserImage());

                isUpdated = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDBUpdated() {
        return isUpdated;
    }

}