@Database(entities = [User::class, Message::class, Contact::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "astracomm.db"
                ).fallbackToDestructiveMigration().build().also { instance = it }
            }
        }
    }
}

@Dao interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE assignedId = :id")
    suspend fun getUserById(id: String): User?
}

@Dao interface MessageDao {
    @Insert
    suspend fun insert(message: Message)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessages(chatId: String): Flow<List<Message>>
}

@Dao interface ContactDao {
    @Insert
    suspend fun addContact(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>
}