# messaggio :postbox:
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

What? One more Kotlin library? Yes! Messaggio is a simple library that wants to hug you and ease your pain
associated with how to display toasts, snackbars or any kind of message you want (but you'll have to implement it by yourself, oops) according to MVI/MVP/MVVM paradigm.
The library is inspired by [Cicerone](https://github.com/terrakok/cicerone), btw.

## Get started
Add this stuff to your `build.gradle` file:

```gradle
implementation "com.thunderdogge:messaggio:1.0.0"
```

Register `Messenger` and `MessageDispatcher` using your favorite DI framework. I prefer [Toothpick](https://github.com/stephanenicolas/toothpick):
```
object AppModule : Module() {
    init {
        val messaggio = Messaggio.create()
        bind(Messenger::class.java).toInstance(messaggio.messenger)
        bind(MessageDispatcher::class.java).toInstance(messaggio.dispatcher)
    }
}
```

Add `MessageReceiver` and `MessageDispatcher` instances to activity:
```
class MyActivity : AppCompatActivity() {
    private val messageReceiver = MainReceiver(this)

    private val messageDispatcher = scope.getInstance(MessageDispatcher::class.java)
}
```

Attach `messageReceiver` to `messageDispatcher` and don't forget to detach receiver to avoid memory leaks:
```
override fun onResumeFragments() {
    super.onResumeFragments()

    messageDispatcher.attachReceiver(messageReceiver)
}

override fun onPause() {
    messageDispatcher.detachReceiver()

    super.onPause()
}
```

That's it. You're ready to rock!
```
class MyViewModel @Inject constructor(
    private val messenger: Messenger
) : ViewModel() {
    fun onShowMessageClick() {
        messenger.showToast("Hi there!")
    }
}
```

## Custom messages
If you realized that you need more than just toast and snackbar, you could add the support of any kind of custom message.

Create new class that implements `IMessage`:
```
class DialogMessage(
    val title: String,
    val text: String
) : IMessage
```

Create your own `MessageReceiver` and handle new message type there:
```
class MyMessageReceiver(private val activity: Activity) : MessageReceiver(activity) {
    override fun onMessageReceived(message: IMessage) {
        if (message is DialogMessage) {
            showDialogMessage(message)
            return
        }

        super.onMessageReceived(message)
    }

    private fun showDialogMessage(message: DialogMessage) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(message.title)
            .setMessage(message.text)
            .setPositiveButton("OK", null)
            .show()
    }
}
```

Also it's good idea to add useful extension:
```
fun IMessenger.showDialog(title: String, text: String) {
    val message = DialogMessage(title, text)
    postMessage(message)
}
```

## Oh man, I need a sample
I've got one, [here you go](https://github.com/thunderdogge/messaggio/tree/master/sample).
And feel free to contact me via Telegram: [@thunderdogge](https://t.me/thunderdogge).

## License
```
MIT License

Copyright (c) 2020 Alexey Kolesov

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```