/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
   public User getUser(String name) {
       for(int i=0;i<userCount;i++){
        if(users[i].getName().equalsIgnoreCase(name)){
            return users[i];
        }
       }
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if(userCount==users.length){
            return false;
        }
        if(getUser(name)!=null){
            return false;
        }
        users[userCount]= new User(name);
        userCount++;
        return true;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
          User u1=getUser(name1);
        User u2=getUser(name2);
    
        if(u1==null || u2==null){
        return false;
    }
        if(name1.equalsIgnoreCase(name2)){
            return false;
        }
      
    return u1.addFollowee(name2);
}
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
         int x=0; String rec="";
       for(int i=0;i<userCount;i++){
        if(getUser(name).follows((users[i].getName()))==false && getUser(name)!=users[i]){
            if(getUser(name).countMutual(users[i])>x){
                x=getUser(name).countMutual(users[i]);
                rec=users[i].getName();
            }
        }
       }
        return rec;
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
       if(userCount==0){
        return "null";
       }
       int [] popular=new int [userCount];
       for(int i=0;i<userCount;i++){
        for(int j=0;j<userCount;j++){
            if(users[j].follows(users[i].getName())){
                popular [i]++;
            }
        }
       }
       int max=-1;
       int indexmax=-1;
       for(int i=0;i<userCount;i++){
        if(popular[i]>max){
            max=popular[i];
            indexmax=i;
        }
       }
       if(indexmax==-1 || max==0){
        return "null";
       }
       return users[indexmax].getName();
    }
    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int counter=0;
        for(int i=0;i<userCount;i++){
            if(users[i].follows(name)==true){
                counter++;
            }
        }
        return counter;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
        String str="Network:";
       for(int i=0;i<userCount;i++){
         str+="\n" + users[i].getName() + " -> ";
        for(int j=0;j<users[i].getfCount();j++){
                str+=users[i].getfFollows()[j] + " ";
            }
        }
       
       return str;
    }
}