@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey val id: String,
    val participantIds: List<String>,
    val lastMessage: String,
    val timestamp: Long
)