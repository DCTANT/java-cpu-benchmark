# 带GUI界面的jcpu-benchmark

## native打包
### 目前打包启动后的项目报错，不知道如何解决，折腾了3,4个小时了，还是不行
java -agentlib:native-image-agent=config-output-dir=./cdir -jar jcpu-benchmark-ui-1.3.1-SNAPSHOT.jar

native-image -H:+ReportExceptionStackTraces -H:IncludeResources="classes/font/*" --no-fallback --link-at-build-time -H:ConfigurationFileDirectories=./cdir -H:+AddAllCharsets --report-unsupported-elements-at-runtime --enable-url-protocols=https,http -jar jcpu-benchmark-ui-1.3.1-SNAPSHOT.jar


Windows系统后报错：

Exception in thread "AWT-EventQueue-0" java.lang.Error: java.home property not set
at java.desktop@23.0.1/sun.awt.FontConfiguration.findFontConfigFile(FontConfiguration.java:180)
at java.desktop@23.0.1/sun.awt.FontConfiguration.<init>(FontConfiguration.java:97)
at java.desktop@23.0.1/sun.awt.windows.WFontConfiguration.<init>(WFontConfiguration.java:41)
at java.desktop@23.0.1/sun.awt.Win32FontManager.createFontConfiguration(Win32FontManager.java:175)
at java.desktop@23.0.1/sun.font.SunFontManager$2.run(SunFontManager.java:352)
at java.desktop@23.0.1/sun.font.SunFontManager$2.run(SunFontManager.java:309)
at java.base@23.0.1/java.security.AccessController.executePrivileged(AccessController.java:132)
at java.base@23.0.1/java.security.AccessController.doPrivileged(AccessController.java:319)
at java.desktop@23.0.1/sun.font.SunFontManager.<init>(SunFontManager.java:309)
at java.desktop@23.0.1/sun.awt.Win32FontManager.<init>(Win32FontManager.java:83)
at java.desktop@23.0.1/sun.font.PlatformFontInfo.createFontManager(PlatformFontInfo.java:37)
at java.desktop@23.0.1/sun.font.FontManagerFactory.getInstance(FontManagerFactory.java:51)
at java.desktop@23.0.1/sun.font.SunFontManager.getInstance(SunFontManager.java:242)
at java.desktop@23.0.1/sun.font.FontDesignMetrics.getMetrics(FontDesignMetrics.java:260)
at java.desktop@23.0.1/sun.swing.SwingUtilities2.getFontMetrics(SwingUtilities2.java:1242)
at java.desktop@23.0.1/javax.swing.JComponent.getFontMetrics(JComponent.java:1700)
at java.desktop@23.0.1/javax.swing.text.PlainView.calculateLongestLine(PlainView.java:783)
at java.desktop@23.0.1/javax.swing.text.PlainView.updateMetrics(PlainView.java:332)
at java.desktop@23.0.1/javax.swing.text.PlainView.updateDamage(PlainView.java:670)
at java.desktop@23.0.1/javax.swing.text.PlainView.insertUpdate(PlainView.java:591)
at java.desktop@23.0.1/javax.swing.plaf.basic.BasicTextUI$RootView.insertUpdate(BasicTextUI.java:1711)
at java.desktop@23.0.1/javax.swing.plaf.basic.BasicTextUI$UpdateHandler.insertUpdate(BasicTextUI.java:1978)
at java.desktop@23.0.1/javax.swing.text.AbstractDocument.fireInsertUpdate(AbstractDocument.java:227)
at java.desktop@23.0.1/javax.swing.text.AbstractDocument.handleInsertString(AbstractDocument.java:781)
at java.desktop@23.0.1/javax.swing.text.AbstractDocument.insertString(AbstractDocument.java:740)
at java.desktop@23.0.1/javax.swing.text.PlainDocument.insertString(PlainDocument.java:131)
at java.desktop@23.0.1/javax.swing.text.AbstractDocument.replace(AbstractDocument.java:699)
at java.desktop@23.0.1/javax.swing.text.JTextComponent.setText(JTextComponent.java:1725)
at java.desktop@23.0.1/javax.swing.JTextArea.<init>(JTextArea.java:221)
at java.desktop@23.0.1/javax.swing.JTextArea.<init>(JTextArea.java:159)
at com.itdct.cbench.ui.frame.MainFrame.createTextArea(MainFrame.java:159)
at com.itdct.cbench.ui.frame.MainFrame.<init>(MainFrame.java:69)
at com.itdct.cbench.ui.Main.lambda$main$0(Main.java:24)
at java.desktop@23.0.1/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
at java.desktop@23.0.1/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:773)
at java.desktop@23.0.1/java.awt.EventQueue$4.run(EventQueue.java:720)
at java.desktop@23.0.1/java.awt.EventQueue$4.run(EventQueue.java:714)
at java.base@23.0.1/java.security.AccessController.executePrivileged(AccessController.java:132)
at java.base@23.0.1/java.security.AccessController.doPrivileged(AccessController.java:400)
at java.base@23.0.1/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:87)
at java.desktop@23.0.1/java.awt.EventQueue.dispatchEvent(EventQueue.java:742)
at java.desktop@23.0.1/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
at java.desktop@23.0.1/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
at java.desktop@23.0.1/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
at java.desktop@23.0.1/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
at java.desktop@23.0.1/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
at java.desktop@23.0.1/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:832)
at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:808)
