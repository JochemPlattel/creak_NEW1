package creak

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GLUtil

class Window(val width: Int = 500, val height: Int = 500, val title: String = "My Creak Project") {
    var id = 0L
    fun open() {
        //glfw setup
        glfwInit()
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE)
        id = glfwCreateWindow(width, height, title, 0L, 0L)
        glfwMakeContextCurrent(id)
        glfwShowWindow(id)

        //opengl setup
        createCapabilities()
        GLUtil.setupDebugMessageCallback()
        glClearColor(0.5f, .7f, 0.5f, 1f)
    }
    fun run(updateFunction: () -> Unit) {
        while (!glfwWindowShouldClose(id)) {
            glfwPollEvents()
            updateLoop(updateFunction)
            glfwSwapBuffers(id)
        }
    }
    fun updateLoop(updateFunction: () -> Unit) {
        glClear(GL_COLOR_BUFFER_BIT)
        updateFunction()
    }
}