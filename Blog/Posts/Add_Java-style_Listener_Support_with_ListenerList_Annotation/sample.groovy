// Simple listener interface with spoke() method.
interface TalkListener {
    void spoke(TalkEvent event)
}

// Event to be passed into listener spoke() method.
class TalkEvent {
    String text, origin
}

class Speaker {
    @groovy.beans.ListenerList
    List&lt;TalkListener&gt; talkListeners

    String name

    void sayHello() {
        // fireSpoke is added by @ListenerList.
        fireSpoke(new TalkEvent(origin: name, text: 'Hello Groovy world!'))
    }

    // Methods generated by @ListenerList:
    // void addTalkListener(TalkListener)
    // void removeTalkListener(TalkListener)
    // TalkListener[] getTalkListeners()
    // void fireSpoke(TalkEvent)
}


def s = new Speaker(name: 'mrhaki')
// Add listener interface implementation to simply output
// the data in the event object.
s.addTalkListener([spoke: { event -&gt;
   println "$event.origin says '${event.text.toLowerCase()}'"
}] as TalkListener)
// Second implemenation of listener interface is added.
def shouter = { event -&gt; println "${event.text.toUpperCase()}" } as TalkListener
s.addTalkListener shouter
s.sayHello()

// Output:
// mrhaki says 'hello groovy world!'
// HELLO GROOVY WORLD!
