typedef struct Spinlock{
    int flag;
}* spinlock;
void spinlock_get();
void spinlock_release();