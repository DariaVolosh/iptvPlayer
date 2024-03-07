CC := C:\Users\volos\AppData\Local\Android\Sdk\ndk\25.1.8937393\toolchains\llvm\prebuilt\windows-x86_64\bin\clang++
CFLAGS := --sysroot=C:\Users\volos\AppData\Local\Android\Sdk\ndk\25.1.8937393\toolchains\llvm\prebuilt\windows-x86_64\sysroot
LDFLAGS := -landroid -latomic
SRC := E:\media3\media-release\libraries\decoder_ffmpeg\src\main\jni\ffmpeg_jni.cc
OUTPUTDIR := E:\android_studio_projects\projects\IPTVPlayer\app\src\main\jniLibs\x86\libffmpeg.o
LDLIBS := E:\media3\media-release\libraries\decoder_ffmpeg\src\main\jni\ffmpeg\android-libs\x86\libswresample.a E:/media3/media-release/libraries/decoder_ffmpeg/src/main/jni/ffmpeg/android-libs/x86/libavcodec.a E:/media3/media-release/libraries/decoder_ffmpeg/src/main/jni/ffmpeg/android-libs/x86/libavutil.a

$(OUTPUTDIR): $(SRC) $(LDLIBS)
	$(CC) $(SRC) $(CFLAGS) -o $(OUTPUTDIR) $(LDFLAGS) $(LDLIBS)
rule: $(OUTPUTDIR)