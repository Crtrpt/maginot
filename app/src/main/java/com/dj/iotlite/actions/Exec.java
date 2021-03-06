package com.dj.iotlite.actions;

import android.util.Log;

import com.dj.iotlite.events.LogEvent;

import net.crtrpt.EvalVisitor;
import net.crtrpt.Function;
import net.crtrpt.Scope;
import net.crtrpt.TLValue;
import net.crtrpt.gen.TLLexer;
import net.crtrpt.gen.TLParser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.greenrobot.eventbus.EventBus;

import java.util.BitSet;
import java.util.HashMap;


public class Exec implements Actions{
    @Override
    public void run(ActionPayload actionPayload) {
        Log.d(this.getClass().getName(), "远程代码执行");
        EventBus.getDefault().post(new LogEvent("远程代码执行"+(String) actionPayload.getPayload()));

        //执行
        TLLexer lexer = new TLLexer(CharStreams.fromString((String) actionPayload.getPayload()));
        TLParser parser = new TLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new ANTLRErrorListener(){

            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                EventBus.getDefault().post(new LogEvent("代码解析错误 line:"+line+"  msg:"+msg));
            }

            @Override
            public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
                EventBus.getDefault().post(new LogEvent("发生错误"));
            }

            @Override
            public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
                EventBus.getDefault().post(new LogEvent("发生错误"));
            }

            @Override
            public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
                EventBus.getDefault().post(new LogEvent("发生错误"));
            }
        });
        ParseTree tree =parser.parse();
        //增加内嵌的动态能力
        HashMap<String, Function> functions=new HashMap<>();

        EvalVisitor visitor = new EvalVisitor(new Scope(),functions);
        TLValue v= visitor.visit(tree);
    }
}
