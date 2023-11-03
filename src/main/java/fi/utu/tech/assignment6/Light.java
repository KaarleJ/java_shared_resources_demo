package fi.utu.tech.assignment6;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Light {

    private boolean powerOn = false;
    private int id;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 
     * @param id The id of the lamp (should be the same as in the hub for now)
     */
    public Light(int id) {
        this.id = id;
    }

    /**
     * Turn lamp on
     */
    public void turnOn() {
        lock.writeLock().lock();
        try {
            this.powerOn = true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Turn lamp off
     */
    public void turnOff() {
        lock.writeLock().lock();
        try {
            this.powerOn = false;
        } finally {
            lock.writeLock().unlock();
        } 
    }

    /**
     * Toggle the lamp on/off depending on the current state
     */
    public void toggle() {
        lock.writeLock().lock();
        try {
            this.powerOn = !this.powerOn;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * 
     * @return Is the lamp currently powered on?
     */
    public boolean isPowerOn() {
        lock.readLock().lock();
        try {
            return this.powerOn;
        } finally {
            lock.readLock().unlock();
        }
    }

    public String toString() {
        lock.readLock().lock();
        try {
            return String.format("Light %d is set %s", id, isPowerOn() ? "ON": "OFF");
        } finally {
            lock.readLock().unlock();
        }
    }
    
}
