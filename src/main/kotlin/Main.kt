import creak.*
import org.lwjgl.opengl.GL20.*

fun main() {
    val window = Window()
    window.open()
    start()
    window.run(::update)
}


fun start() {
    val vertices = floatArrayOf(
        -.5f, -.5f, 1f, 0f, 0f,
        .5f, -.5f, 0f, 1f, 0f,
        -.5f, .5f, 0f, 0f, 1f,
        .5f, .5f, 1f, 1f, 0f
    )

    val indices = intArrayOf(
        0, 1, 2,
        1, 2, 3
    )

    val vbo = VBO(BufferUsage.STATIC_DRAW)
    vbo.init(vertices)
    vbo.bind()

    val ebo = EBO(BufferUsage.STATIC_DRAW)
    ebo.init(indices)
    ebo.bind()

    vertexShader.create()
    vertexShader.compile()

    fragmentShader.create()
    fragmentShader.compile()

    program.create()

    setAttributePointers(intArrayOf(2, 3))
}

val vertexShaderSource = """
        #version 400
        
        layout (location = 0) in vec2 aPos;
        layout (location = 1) in vec3 aColor;
        out vec3 color;
        
        void main() {
            color = aColor;
            gl_Position = vec4(aPos.x, aPos.y, 0., 1.);
        }
    """.trimIndent()

val fragmentShaderSource = """
        #version 400
        in vec3 color;
        out vec4 outColor;
        
        void main() {
            outColor = vec4(color, 1.);
        }
    """.trimIndent()

val vertexShader = VertexShader(vertexShaderSource)
val fragmentShader = FragmentShader(fragmentShaderSource)
val program = ShaderProgram(vertexShader, fragmentShader)

fun update() {
    program.use()
    glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)
}